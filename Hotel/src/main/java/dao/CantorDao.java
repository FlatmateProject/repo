package dao;

import dto.SimpleNameData;
import dto.cantor.CurrencyData;
import dto.cantor.CustomerData;
import exception.DAOException;

import java.util.List;

public interface CantorDao {

    List<SimpleNameData> showColumnsForCurrency() throws DAOException;

    List<CurrencyData> findAllCurrency() throws DAOException;

    List<SimpleNameData> showColumnsForCustomer() throws DAOException;

    List<CustomerData> findAllCustomers(String customerId) throws DAOException;
}
