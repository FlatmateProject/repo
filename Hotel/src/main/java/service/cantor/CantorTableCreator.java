package service.cantor;

import dao.CantorDao;
import dto.SimpleNameData;
import dto.cantor.CompanyData;
import dto.cantor.CurrencyData;
import dto.cantor.CustomerData;
import exception.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CantorTableCreator {

    @Autowired
    private final CantorDao cantorDao;

    public CantorTableCreator(CantorDao cantorDao) {
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

    public CantorTableResult createCompanyTable(long companyId) {
        try {
            List<SimpleNameData> customerColumns = cantorDao.showColumnsForCompany();
            List<CompanyData> company = cantorDao.findAllCompanies(companyId);
            return TableBuilder.table().columns(customerColumns).data(company).build();
        } catch (Exception e) {
            return CantorTableResult.EMPTY;
        }
    }
}