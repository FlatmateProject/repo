package dto.cantor;

import org.testng.annotations.Test;
import service.cantor.CURRENCY;

import static org.fest.assertions.Assertions.assertThat;

/**
 * User: piotro
 * Date: 12/22/12
 * Time: 3:57 PM
 */
public class CurrencyDataTest {

    @Test
    public void shouldReturnCurrencyEnumByName() {
        // given
        CurrencyData currencyData = new CurrencyData("EUR");

        //when
        CURRENCY currency = currencyData.asEnum();

        // then
        assertThat(currency)
                .isNotNull()
                .isEqualTo(CURRENCY.EUR);
    }


}
