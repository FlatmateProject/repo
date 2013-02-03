package dao;

import dto.ColumnData;
import dto.cantor.CompanyData;
import dto.cantor.CurrencyData;
import dto.cantor.CustomerData;
import exception.DAOException;
import service.cantor.CURRENCY;
import service.cantor.ExchangeCalculation;

import java.util.List;

public interface CantorDao {

    List<ColumnData> showColumnsForCurrency() throws DAOException;

    List<CurrencyData> findAllCurrency() throws DAOException;

    List<ColumnData> showColumnsForCustomer() throws DAOException;

    List<CustomerData> findAllCustomers(long customerId) throws DAOException;

    List<ColumnData> showColumnsForCompany() throws DAOException;

    List<CompanyData> findAllCompanies(long companyId) throws DAOException;

    CurrencyData findCurrencyByName(CURRENCY currency) throws DAOException;

    void insertTransactionForCustomer(ExchangeCalculation calculation) throws DAOException;

    void insertTransactionForCompany(ExchangeCalculation calculation) throws DAOException;

    void updateCurrency(CurrencyData currency) throws DAOException;
}
