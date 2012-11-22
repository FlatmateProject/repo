package dao;

import dto.SimpleNameData;
import dto.cantor.CurrencyData;
import dto.cantor.CustomerData;
import exception.DAOException;

import java.util.List;

public class CantorDaoImpl extends AbstractDao implements CantorDao{

    public List<SimpleNameData> showColumnsForCurrency() throws DAOException {
        String query = "show columns from hotel.waluty;";
        return executeQuery(query, SimpleNameData.class);
    }

    @Override
    public List<CurrencyData> findAllCurrency() throws DAOException {
        String query = "select * from waluty;";
        return executeQuery(query, CurrencyData.class);

    }

    @Override
    public List<SimpleNameData> showColumnsForCustomer() throws DAOException {
        String query = "show columns from hotel.klienci";
        return executeQuery(query, SimpleNameData.class);
    }

    @Override
    public List<CustomerData> findAllCustomers(String customerId) throws DAOException {
        String query = "select * from hotel.klienci" + customerId;
        return executeQuery(query, CustomerData.class);
    }
}
