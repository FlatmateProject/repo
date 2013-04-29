package service.cantor;

import common.tableBuilder.TableContent;
import common.tableBuilder.TableContentBuilder;
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
            return TableContentBuilder.table().columns(currencyColumns).data(currencies).build();
        } catch (DAOException e) {
            return TableContent.EMPTY;
        }
    }

    public TableContent createCustomerTable(long customerId) {
        try {
            List<ColumnData> customerColumns = cantorDao.showColumnsForCustomer();
            List<CustomerData> customers = cantorDao.findAllCustomers(customerId);
            return TableContentBuilder.table().columns(customerColumns).data(customers).build();
        } catch (Exception e) {
            return TableContent.EMPTY;
        }
    }

    public TableContent createCompanyTable(long companyId) {
        try {
            List<ColumnData> customerColumns = cantorDao.showColumnsForCompany();
            List<CompanyData> company = cantorDao.findAllCompanies(companyId);
            return TableContentBuilder.table().columns(customerColumns).data(company).build();
        } catch (Exception e) {
            return TableContent.EMPTY;
        }
    }
}