package assertions;

import org.fest.assertions.Assertions;
import org.fest.assertions.GenericAssert;
import service.cantor.CURRENCY;
import service.cantor.ExchangeCalculation;

public class ExchangeCalculationAssertion extends GenericAssert<ExchangeCalculationAssertion, ExchangeCalculation> {

    private ExchangeCalculationAssertion(ExchangeCalculation actual) {
        super(ExchangeCalculationAssertion.class, actual);
    }

    public static ExchangeCalculationAssertion assertThat(ExchangeCalculation actual) {
        return new ExchangeCalculationAssertion(actual);
    }

    public ExchangeCalculationAssertion isSaleCurrency(CURRENCY sellingCurrency) {
        Assertions.assertThat(actual.getSellingCurrency()).isEqualTo(sellingCurrency);
        return this;
    }

    public ExchangeCalculationAssertion isBuyingCurrency(CURRENCY buyingCurrency) {
        Assertions.assertThat(actual.getBuyingCurrency()).isEqualTo(buyingCurrency);
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
