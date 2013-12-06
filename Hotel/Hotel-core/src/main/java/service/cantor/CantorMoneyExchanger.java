package service.cantor;

import entity.CurrencyData;
import entity.CurrencyExchangeData;
import exception.CantorTransactionCanceledException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.CurrencyExchangeRepository;
import repository.CurrencyRepository;

@Component
public class CantorMoneyExchanger {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    public CurrencyExchangeData calculateExchange(CURRENCY oldCurrency, CURRENCY newCurrency, float amount) {
        try {
            CurrencyData oldCurrencyData = currencyRepository.findByName(oldCurrency.name());
            CurrencyData newCurrencyData = currencyRepository.findByName(newCurrency.name());
            float valueInPLN = amount * oldCurrencyData.getBuyPrice();
            float cost = valueInPLN / newCurrencyData.getSalePrice();
            float gain = amount * oldCurrencyData.getSalePrice() - valueInPLN;
            return CurrencyExchangeData.save(oldCurrencyData, newCurrencyData, amount, cost, gain);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CurrencyExchangeData.ZERO;
    }

    public boolean isTransactionPossible(CurrencyExchangeData calculation) {
        CurrencyData newCurrency = currencyRepository.findByName(calculation.getBuyingCurrency().getName());
        return newCurrency != null && calculation.getCost() <= newCurrency.getQuantity();
    }

    public void exchangeMoney(CurrencyExchangeData calculation) throws CantorTransactionCanceledException {
        try {
            exchangeMoneyInSingleTransaction(calculation);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CantorTransactionCanceledException(e);
        }
    }

    private void exchangeMoneyInSingleTransaction(CurrencyExchangeData calculation) throws CantorTransactionCanceledException {
        CurrencyData oldCurrency = calculation.getSellingCurrency();
        CurrencyData newCurrency = calculation.getBuyingCurrency();
        CurrencyExchangeData savedData = null;
        if (calculation.isCustomer()) {
            savedData = currencyExchangeRepository.save(calculation);
        } else if (calculation.isCompany()) {
            savedData = currencyExchangeRepository.save(calculation);
        }
        if (savedData == null) {
            throw new CantorTransactionCanceledException();
        }
        oldCurrency.increaseQuantity(calculation.getAmount());
        currencyRepository.save(oldCurrency);
        newCurrency.decreaseQuantity(calculation.getCost());
        currencyRepository.save(newCurrency);
    }

    public void setCurrencyRepository(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public void setCurrencyExchangeRepository(CurrencyExchangeRepository currencyExchangeRepository) {
        this.currencyExchangeRepository = currencyExchangeRepository;
    }
}
