package repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import entity.CurrencyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import service.cantor.CURRENCY;
import spring.ApplicationConfiguration;

import static assertions.CurrencyDataAssertion.assertThat;

@ContextConfiguration(classes = ApplicationConfiguration.class)
@TestExecutionListeners({DbUnitTestExecutionListener.class})
@DatabaseSetup("/dataset/CurrencyRepositoryTest_tearDown.xml")
public class CurrencyRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    CurrencyRepository repository;

    @Test
    @DatabaseSetup("/dataset/CurrencyRepositoryTest_testFindCurrencyByName.xml")
    public void testFindCurrencyByName() throws Exception {
        //given

        //when
        CurrencyData currencyData = repository.findByName(CURRENCY.EUR.name());

        //then
        assertThat(currencyData)
                .isNotNull()
                .hasName(CURRENCY.EUR);
    }
}
