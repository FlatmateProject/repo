package service.cantor;

import dao.CantorDao;
import dao.Singleton;
import dto.SimpleNameData;
import dto.cantor.CompanyData;
import dto.cantor.CurrencyData;
import dto.cantor.CustomerData;
import exception.DAOException;
import validation.ValidationUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Cantor {

    private final Singleton singleton = Singleton.getInstance();

    private final CantorDao cantorDao;

    public Cantor(CantorDao cantorDao) {
        this.cantorDao = cantorDao;
    }

    public CantorTableResult createCurrencyTable() {
        try {
            List<SimpleNameData> currencyColumns = cantorDao.showColumnsForCurrency();
            List<CurrencyData> currencies = cantorDao.findAllCurrency();
            return TableBuilder.table().columns(currencyColumns).data(currencies).build();
        } catch (DAOException e) {
            return CantorTableResult.EMPTY;
        }
    }

    public CantorTableResult createCustomerTable(long customerId) {
        try {
            List<SimpleNameData> customerColumns = cantorDao.showColumnsForCustomer();
            List<CustomerData> customers = cantorDao.findAllCustomers(customerId);

            return TableBuilder.table().columns(customerColumns).data(customers).build();
        } catch (Exception e) {
            return CantorTableResult.EMPTY;
        }
    }

    public CantorTableResult createCompanyTable(String companyId) {
        try {
            List<SimpleNameData> customerColumns = cantorDao.showColumnsForCompany();
            List<CompanyData> company = cantorDao.findAllComparable(companyId);
            return TableBuilder.table().columns(customerColumns).data(company).build();
        } catch (Exception e) {
            return CantorTableResult.EMPTY;
        }
    }

    public ExchangeCalculation calculateExchange(CURRENCY oldCurrency, CURRENCY newCurrency, float amount) {
        try {
            CurrencyData oldCurrencyData = cantorDao.findCurrencyByName(oldCurrency);
            CurrencyData newCurrencyData = cantorDao.findCurrencyByName(newCurrency);
            float valueInPLN = amount * oldCurrencyData.getBuyPrice();
            float cost = valueInPLN / newCurrencyData.getSalePrice();
            float gain = amount * oldCurrencyData.getSalePrice() - valueInPLN;
            return ExchangeCalculation.save(oldCurrency, newCurrency, amount, cost, gain);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ExchangeCalculation.ZERO;
    }


    public boolean isTransactionPossible(ExchangeCalculation calculation) {
        try {
            CurrencyData currency = cantorDao.findCurrencyByName(calculation.getBuyingCurrency());
            return calculation.getCost() <= currency.getQuantity();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean exchangeMoney(String clientId, ExchangeCalculation calculation) {
        try {
            CurrencyData change1 = cantorDao.findCurrencyByName(calculation.getSellingCurrency());
            CurrencyData change2 = cantorDao.findCurrencyByName(calculation.getBuyingCurrency());
            if (calculation.getCost() > change2.getQuantity()) {
                return false;
            }
            if (ValidationUtils.isPesel(clientId)) {
                insertTransaction(clientId, calculation, "IDK_PESEL");

            } else {
                insertTransaction(clientId, calculation, "IDF_KRS");
            }
            change1.increaseQuantity(calculation.getAmount());
            updateCurrency(change1);

            change2.decreaseQuantity(calculation.getCost());
            updateCurrency(change2);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void insertTransaction(String id, ExchangeCalculation calculation, String idColumn) {
        singleton.queryUp("insert into hotel.kantor (" + idColumn + ", DATA, WALUTA_1, WALUTA_2, WARTOSC_1, WARTOSC_2, ZYSK) VALUES("
                + id + ", '" + showCurrentDate() + "', '" + calculation.getSellingCurrency() + "', '" + calculation.getBuyingCurrency()
                + "', " + calculation.getAmount() + ", " + calculation.getCost() + ", " + calculation.getGain() + ")");
    }

    private void updateCurrency(CurrencyData currencyData) {
        singleton.queryUp("update hotel.waluty set ILOSC=" + currencyData.getQuantity() + " where NAZWA = '" + currencyData.getName() + "';");
    }

    private String showCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
}
