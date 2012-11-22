package service.cantor;

import dao.CantorDao;
import dao.Singleton;
import dto.SimpleNameData;
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

    public CantorTableResult createClientTable(String s1) {
        try {
            List<SimpleNameData> customerColumns = cantorDao.showColumnsForCustomer();
            List<CustomerData> customers = cantorDao.findAllCustomers(s1);
            return TableBuilder.table().columns(customerColumns).data(customers).build();
        } catch (Exception e) {
            return CantorTableResult.EMPTY;
        }
    }

    public CantorTableResult createCompTable(String s1) {
        try {
            int i = 0, cols, rows;
            ResultSet rset5 = singleton.query("show columns from hotel.firmy");
            ResultSet rset6 = singleton.query("select * from hotel.firmy" + s1);
            rset5.last();
            cols = rset5.getRow();
            rset6.last();
            rows = rset6.getRow();
            Object rowData[][] = new Object[rows][cols];
            String columnNames[] = new String[cols];
            rset5.first();
            rset6.first();
            do {
                columnNames[i] = rset5.getString(1);
                i++;
            } while (rset5.next());
            i = 0;
            do {
                for (int j = 0; j < cols; j++) {
                    rowData[i][j] = rset6.getString(j + 1);
                }
                i++;
            } while (rset6.next());
            return CantorTableResult.store(rowData, columnNames);
        } catch (Exception e) {
        }
        return null;
    }

    public String trimInput(String input) {
        return input.replaceAll("\\D*", "");
    }

    public float[] changeCalc(String s1, String s2, float much) {
        float curr1 = 0, curr2 = 0, curr3 = 0, cost = 0, temp = 0, temp1 = 0, temp2 = 0;
        float resulRet[] = new float[3];
        try {
            ResultSet change1 = singleton.query("select * from hotel.waluty where NAZWA=" + "'" + s1 + "'");
            change1.first();
            curr1 = change1.getFloat("CENA_KU");
            curr2 = change1.getFloat("CENA_SP");

            ResultSet change2 = singleton.query("select * from hotel.waluty where NAZWA=" + "'" + s2 + "'");
            change2.first();
            curr3 = change2.getFloat("CENA_SP");
            if (s1 == "PLN") {
                cost = much * curr3;
            } else if (s2 == "PLN") {
                cost = much * curr1;
            } else {
                temp = much * curr1;
                temp1 = temp / curr2;
                temp2 = temp1 * curr1;
                cost = temp2 / curr3;
                //
//                log.info("Dostaniesz: " + cost + ", zysk: " + (temp - temp2));
                resulRet[0] = much;
                resulRet[1] = cost;
                resulRet[2] = temp - temp2;
            }

        } catch (Exception e) {
//            log.info("Brak danych");
        }
        return resulRet;
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
