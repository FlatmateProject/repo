package service.cantor;

import common.tableBuilder.TableContent;
import dao.CantorDao;
import dto.ColumnData;
import dto.CompanyData;
import dto.CurrencyData;
import dto.CustomerData;
import exception.DAOException;

import java.util.List;

public class CantorTableCreator {

    private final CantorDao cantorDao;

    public CantorTableCreator(CantorDao cantorDao) {
        this.cantorDao = cantorDao;
    }

    public TableContent createCurrencyTable() {
        try {
            List<ColumnData> currencyColumns = cantorDao.showColumnsForCurrency();
            List<CurrencyData> currencies = cantorDao.findAllCurrency();
            return TableContent.store(currencyColumns, currencies);
        } catch (DAOException e) {
            return TableContent.EMPTY;
        }
    }

    public TableContent createCustomerTable(long customerId) {
        try {
            List<ColumnData> customerColumns = cantorDao.showColumnsForCustomer();
            List<CustomerData> customers = cantorDao.findAllCustomers(customerId);
            return TableContent.store(customerColumns, customers);
        } catch (Exception e) {
            return TableContent.EMPTY;
        }
    }

    public TableContent createCompanyTable(long companyId) {
        try {
            List<ColumnData> customerColumns = cantorDao.showColumnsForCompany();
            List<CompanyData> company = cantorDao.findAllCompanies(companyId);
            return TableContent.store(customerColumns, company);
        } catch (Exception e) {
            return TableContent.EMPTY;
        }
    }
}