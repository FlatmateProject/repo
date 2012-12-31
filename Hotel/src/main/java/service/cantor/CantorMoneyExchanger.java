package service.cantor;

import dao.CantorDao;
import dto.cantor.CurrencyData;
import exception.CantorTransactionCanceledException;
import exception.DAOException;

public class CantorMoneyExchanger {

    private final CantorDao cantorDao;

    public CantorMoneyExchanger(CantorDao cantorDao) {
        this.cantorDao = cantorDao;
    }

    public ExchangeCalculation calculateExchange(CURRENCY oldCurrency, CURRENCY newCurrency, float amount) {
        try {
            CurrencyData oldCurrencyData = cantorDao.findCurrencyByName(oldCurrency);
            CurrencyData newCurrencyData = cantorDao.findCurrencyByName(newCurrency);
            float valueInPLN = amount * oldCurrencyData.getBuyPrice();
            float cost = valueInPLN / newCurrencyData.getSalePrice();
            float gain = amount * oldCurrencyData.getSalePrice() - valueInPLN;
            return ExchangeCalculation.save(oldCurrencyData, newCurrencyData, amount, cost, gain);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ExchangeCalculation.ZERO;
    }


    public boolean isTransactionPossible(ExchangeCalculation calculation) {
        try {
            CurrencyData newCurrency = cantorDao.findCurrencyByName(calculation.getBuyingCurrency().asEnum());
            return calculation.getCost() <= newCurrency.getQuantity();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void exchangeMoney(ExchangeCalculation calculation) throws CantorTransactionCanceledException {
        try {
            exchangeMoneyInSingleTransaction(calculation);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CantorTransactionCanceledException();
        }
    }

    private void exchangeMoneyInSingleTransaction(ExchangeCalculation calculation) throws DAOException {
        CurrencyData oldCurrency = calculation.getSellingCurrency();
        CurrencyData newCurrency = calculation.getBuyingCurrency();
        if (calculation.isCustomer()) {
            cantorDao.insertTransactionForCustomer(calculation);
        } else if (calculation.isCompany()) {
            cantorDao.insertTransactionForCompany(calculation);
        } else {
            return;
        }
        oldCurrency.increaseQuantity(calculation.getAmount());
        cantorDao.updateCurrency(oldCurrency);
        newCurrency.decreaseQuantity(calculation.getCost());
        cantorDao.updateCurrency(newCurrency);
    }
}
