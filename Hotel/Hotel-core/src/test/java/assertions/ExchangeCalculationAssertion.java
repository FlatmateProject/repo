package assertions;

import entity.ExchangeCalculationData;
import org.fest.assertions.Assertions;
import org.fest.assertions.GenericAssert;
import service.cantor.CURRENCY;

public class ExchangeCalculationAssertion extends GenericAssert<ExchangeCalculationAssertion, ExchangeCalculationData> {

    private ExchangeCalculationAssertion(ExchangeCalculationData actual) {
        super(ExchangeCalculationAssertion.class, actual);
    }

    public static ExchangeCalculationAssertion assertThat(ExchangeCalculationData actual) {
        return new ExchangeCalculationAssertion(actual);
    }

    public ExchangeCalculationAssertion isSaleCurrency(CURRENCY sellingCurrency) {
        Assertions.assertThat(actual.getSellingCurrency().asEnum()).isEqualTo(sellingCurrency);
        return this;
    }

    public ExchangeCalculationAssertion isBuyingCurrency(CURRENCY buyingCurrency) {
        Assertions.assertThat(actual.getBuyingCurrency().asEnum()).isEqualTo(buyingCurrency);
        return this;
    }

    public ExchangeCalculationAssertion isAmount(float amount) {
        Assertions.assertThat(actual.getAmount()).isEqualTo(amount);
        return this;
    }

    public ExchangeCalculationAssertion isCost(float cost) {
        Assertions.assertThat(actual.getCost()).isEqualTo(cost);
        return this;
    }

    public ExchangeCalculationAssertion isGain(float gain) {
        Assertions.assertThat(actual.getGain()).isEqualTo(gain);
        return this;
    }
}
