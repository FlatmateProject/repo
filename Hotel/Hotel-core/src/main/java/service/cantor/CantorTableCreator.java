package service.cantor;

import common.tableBuilder.TableContent;
import dictionary.TABLE;
import dto.ColumnData;
import entity.CompanyData;
import entity.CurrencyData;
import entity.CustomerData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.CompanyRepository;
import repository.CurrencyRepository;
import repository.CustomerRepository;

import java.util.Arrays;
import java.util.List;

@Component
public class CantorTableCreator {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    public TableContent createCurrencyTable() {
        List<ColumnData> currencyColumns = TableContent.asList(TABLE.Currency);
        Iterable<CurrencyData> currencies = currencyRepository.findAll();
        return TableContent.store(currencyColumns, currencies);
    }

    public TableContent createCustomerTable(long customerId) {
        List<ColumnData> customerColumns = TableContent.asList(TABLE.Customer);
        CustomerData customer = customerRepository.findOne(customerId);
        if (customer == null) {
            return TableContent.emptyContent(customerColumns);
        }
        return TableContent.store(customerColumns, Arrays.asList(customer));
    }

    public TableContent createCompanyTable(long companyId) {
        List<ColumnData> companyColumns = TableContent.asList(TABLE.Company);
        CompanyData company = companyRepository.findOne(companyId);
        if (company == null) {
            return TableContent.emptyContent(companyColumns);
        }
        return TableContent.store(companyColumns, Arrays.asList(company));
    }

    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void setCurrencyRepository(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
}