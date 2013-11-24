package service.cantor;

import entity.CurrencyData;
import entity.ExchangeCalculationData;
import exception.CantorTransactionCanceledException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.CurrencyRepository;
import repository.ExchangeCalculationRepository;

@Component
public class CantorMoneyExchanger {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private ExchangeCalculationRepository exchangeCalculationRepository;

    public ExchangeCalculationData calculateExchange(CURRENCY oldCurrency, CURRENCY newCurrency, float amount) {
        try {
            CurrencyData oldCurrencyData = currencyRepository.findByName(oldCurrency);
            CurrencyData newCurrencyData = currencyRepository.findByName(newCurrency);
            float valueInPLN = amount * oldCurrencyData.getBuyPrice();
            float cost = valueInPLN / newCurrencyData.getSalePrice();
            float gain = amount * oldCurrencyData.getSalePrice() - valueInPLN;
            return ExchangeCalculationData.save(oldCurrencyData, newCurrencyData, amount, cost, gain);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ExchangeCalculationData.ZERO;
    }

    public boolean isTransactionPossible(ExchangeCalculationData calculation) {
        CurrencyData newCurrency = currencyRepository.findByName(calculation.getBuyingCurrency().asEnum());
        return newCurrency != null && calculation.getCost() <= newCurrency.getQuantity();
    }

    public void exchangeMoney(ExchangeCalculationData calculation) throws CantorTransactionCanceledException {
        try {
            exchangeMoneyInSingleTransaction(calculation);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CantorTransactionCanceledException(e);
        }
    }

    private void exchangeMoneyInSingleTransaction(ExchangeCalculationData calculation) throws CantorTransactionCanceledException {
        CurrencyData oldCurrency = calculation.getSellingCurrency();
        CurrencyData newCurrency = calculation.getBuyingCurrency();
        ExchangeCalculationData savedData = null;
        if (calculation.isCustomer()) {
            savedData = exchangeCalculationRepository.save(calculation);
        } else if (calculation.isCompany()) {
            savedData = exchangeCalculationRepository.save(calculation);
        }
        if (savedData == null) {
            throw new CantorTransactionCanceledException("wrong client");
        }
        oldCurrency.increaseQuantity(calculation.getAmount());
        currencyRepository.save(oldCurrency);
        newCurrency.decreaseQuantity(calculation.getCost());
        currencyRepository.save(newCurrency);
    }

    public void setCurrencyRepository(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public void setExchangeCalculationRepository(ExchangeCalculationRepository exchangeCalculationRepository) {
        this.exchangeCalculationRepository = exchangeCalculationRepository;
    }
}
