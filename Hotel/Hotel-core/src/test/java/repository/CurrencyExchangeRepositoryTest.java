package repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import entity.CurrencyData;
import entity.CurrencyExchangeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import service.cantor.CURRENCY;
import spring.ApplicationConfiguration;

import static assertions.CurrencyExchangeDataAssertion.assertThat;

@ContextConfiguration(classes = ApplicationConfiguration.class)
@TestExecutionListeners({DbUnitTestExecutionListener.class})
public class CurrencyExchangeRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    CurrencyExchangeRepository currencyExchangeRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Test
    @DatabaseTearDown("/dataset/CurrencyExchangeRepositoryTest_testSaveCurrency.xml")
    public void testSaveCurrency() throws Exception {
        //given
        String customerId = "87122235514";

        CurrencyData sellingCurrency = currencyRepository.findByName(CURRENCY.EUR.name());

        CurrencyData buyingCurrency = currencyRepository.findByName(CURRENCY.USD.name());

        CurrencyExchangeData currencyExchangeData = CurrencyExchangeData.save(sellingCurrency, buyingCurrency, 100, 200, 300);
        currencyExchangeData.forClient(customerId);

        //when
        CurrencyExchangeData saved = currencyExchangeRepository.save(currencyExchangeData);

        //then
        assertThat(saved)
                .isNotNull()
                .isIdAssign()
                .hasCustomer(customerId);
    }
}
