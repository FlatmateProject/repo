package dao.impl;

import dao.CantorDao;
import dto.ColumnData;
import dto.CompanyData;
import dto.CurrencyData;
import dto.CustomerData;
import exception.DAOException;
import service.cantor.CURRENCY;
import service.cantor.ExchangeCalculation;
import session.SimpleSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CantorDaoImpl implements CantorDao {

    private final SimpleSession session;

    public CantorDaoImpl(SimpleSession session) {
        this.session = session;
    }

    public List<ColumnData> showColumnsForCurrency() throws DAOException {
        String query = "show columns from hotel.waluty;";
        return session.executeQuery(query, ColumnData.class);
    }

    @Override
    public List<CurrencyData> findAllCurrency() throws DAOException {
        String query = "select * from waluty;";
        return session.executeQuery(query, CurrencyData.class);

    }

    @Override
    public List<ColumnData> showColumnsForCustomer() throws DAOException {
        String query = "show columns from hotel.klienci";
        return session.executeQuery(query, ColumnData.class);
    }

    @Override
    public List<CustomerData> findAllCustomers(long customerId) throws DAOException {
        String query = "select * from hotel.klienci where IDK_PESEL=" + customerId;
        return session.executeQuery(query, CustomerData.class);
    }


    @Override
    public List<ColumnData> showColumnsForCompany() throws DAOException {
        String query = "show columns from hotel.firmy";
        return session.executeQuery(query, ColumnData.class);
    }

    @Override
    public List<CompanyData> findAllCompanies(long companyId) throws DAOException {
        String query = "select * from hotel.firmy where IDF_KRS=" + companyId;
        return session.executeQuery(query, CompanyData.class);
    }

    @Override
    public CurrencyData findCurrencyByName(CURRENCY currency) throws DAOException {
        String query = "select * from hotel.waluty where NAZWA=" + "'" + currency + "'";
        return session.uniqueResult(query, CurrencyData.class);
    }

    @Override
    public void insertTransactionForCustomer(ExchangeCalculation calculation) throws DAOException {
        insertTransaction(calculation, "IDK_PESEL");
    }

    @Override
    public void insertTransactionForCompany(ExchangeCalculation calculation) throws DAOException {
        insertTransaction(calculation, "IDF_KRS");
    }

    @Override
    public void updateCurrency(CurrencyData currencyData) throws DAOException {
        session.update("update hotel.waluty set ILOSC=" + currencyData.getQuantity() + " where NAZWA = '" + currencyData.getName() + "';");
    }

    private void insertTransaction(ExchangeCalculation calculation, String idColumn) throws DAOException {
        String query = "insert into hotel.kantor (" + idColumn + ", DATA, W_KU, W_SP, ILOSC, WARTOSC, ZYSK) VALUES("
                + calculation.getClientId() + ", '" + formatCurrentDate() + "', '" + calculation.getSellingCurrency().getCurrencyId() + "', '" + calculation.getBuyingCurrency().getCurrencyId()
                + "', " + calculation.getAmount() + ", " + calculation.getCost() + ", " + calculation.getGain() + ");";
        session.update(query);
    }

    private String formatCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
}
