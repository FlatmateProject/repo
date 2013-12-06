package assertions;

import entity.CurrencyData;
import org.fest.assertions.Assertions;
import org.fest.assertions.GenericAssert;
import service.cantor.CURRENCY;

public class CurrencyDataAssertion extends GenericAssert<CurrencyDataAssertion, CurrencyData> {

    private CurrencyDataAssertion(CurrencyData actual) {
        super(CurrencyDataAssertion.class, actual);
    }

    public static CurrencyDataAssertion assertThat(CurrencyData actual) {
        return new CurrencyDataAssertion(actual);
    }

    public CurrencyDataAssertion hasName(CURRENCY currency) {
        Assertions.assertThat(actual.getName()).isEqualTo(currency.name());
        return this;
    }
}
