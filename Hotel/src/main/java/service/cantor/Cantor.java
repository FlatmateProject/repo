package service.cantor;

import dao.CantorDao;
import dao.Singleton;
import dto.SimpleNameData;
import dto.cantor.CompanyData;
import dto.cantor.CurrencyData;
import dto.cantor.CustomerData;
import exception.DAOException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Cantor {

    private Singleton singleton = Singleton.getInstance();

    private CantorDao cantorDao;

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

    public ExchangeCalculation changeCalc(CURRENCY saleCurrency, CURRENCY buyCurrency, float amount) {
        try {
            CurrencyData currency1 = cantorDao.findCurrencyByName(saleCurrency);
            CurrencyData currency2 = cantorDao.findCurrencyByName(buyCurrency);

            float buyPrice = currency1.getBuyPrice();
            float salePrice1 = currency1.getSalePrice();
            float salePrice2 = currency2.getSalePrice();

            float cost = 0;
            float gain = 0;
            if (saleCurrency == CURRENCY.PLN) {
                cost = amount * salePrice2;
            } else if (buyCurrency == CURRENCY.PLN) {
                cost = amount * buyPrice;
            } else {
                float temp = amount * buyPrice;
                float temp1 = temp / salePrice1;
                float temp2 = temp1 * buyPrice;
                cost = temp2 / salePrice2;
                gain = temp - temp2;
//                log.info("Dostaniesz: " + cost + ", zysk: " + (temp - temp2));
            }
            return ExchangeCalculation.save(amount, cost, gain);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ExchangeCalculation.ZERO;
    }

    public boolean changeMoney(boolean whatIs, String id, String data, String cur1, String cur2, float val1, float val2, float profit) {
        float number = 0, newNumber = 0;
        try {

            ResultSet change1 = singleton.query("select * from hotel.waluty where NAZWA=" + "'" + cur1 + "'");
            ResultSet change2 = singleton.query("select * from hotel.waluty where NAZWA=" + "'" + cur2 + "'");
            change1.first();
            change2.first();
            number = change2.getFloat("ILOSC");
            if (val2 > number)
                return false;
            else {
                if (whatIs) {
                    singleton.queryUp("insert into hotel.kantor (IDK_PESEL, DATA, WALUTA_1, WALUTA_2, WARTOSC_1, WARTOSC_2, ZYSK) VALUES("
                            + id + ", '" + data + "', '" + cur1 + "', '" + cur2 + "', " + val1 + ", " + val2 + ", " + profit + ")");

                } else {
                    singleton.queryUp("insert into hotel.kantor (IDF_KRS, DATA, WALUTA_1, WALUTA_2, WARTOSC_1, WARTOSC_2, ZYSK) VALUES("
                            + id + ", '" + data + "', '" + cur1 + "', '" + cur2 + "', " + val1 + ", " + val2 + ", " + profit + ")");
                }
                change1.first();
                number = change1.getFloat("ILOSC");
                newNumber = number + val1;
                singleton.queryUp("update hotel.waluty set ILOSC=" + (int) newNumber + " where NAZWA = '" + cur1 + "';");
                change2.first();
                number = change2.getFloat("ILOSC");
                newNumber = number - val2;
                singleton.queryUp("update hotel.waluty set ILOSC=" + (int) newNumber + " where NAZWA = '" + cur2 + "';");
            }

        } catch (SQLException e) {
//            log.info("Brak danych");
        }
        return true;
    }

    public String showCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

}
