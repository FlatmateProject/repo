package assertions;

import entity.CurrencyExchangeData;
import org.fest.assertions.Assertions;
import org.fest.assertions.GenericAssert;
import service.cantor.CURRENCY;

public class CurrencyExchangeDataAssertion extends GenericAssert<CurrencyExchangeDataAssertion, CurrencyExchangeData> {

    private CurrencyExchangeDataAssertion(CurrencyExchangeData actual) {
        super(CurrencyExchangeDataAssertion.class, actual);
    }

    public static CurrencyExchangeDataAssertion assertThat(CurrencyExchangeData actual) {
        return new CurrencyExchangeDataAssertion(actual);
    }

    public CurrencyExchangeDataAssertion isSaleCurrency(CURRENCY sellingCurrency) {
        Assertions.assertThat(CURRENCY.valueOf(actual.getSellingCurrency().getName())).isEqualTo(sellingCurrency);
        return this;
    }

    public CurrencyExchangeDataAssertion isBuyingCurrency(CURRENCY buyingCurrency) {
        Assertions.assertThat(CURRENCY.valueOf(actual.getBuyingCurrency().getName())).isEqualTo(buyingCurrency);
        return this;
    }

    public CurrencyExchangeDataAssertion isAmount(float amount) {
        Assertions.assertThat(actual.getAmount()).isEqualTo(amount);
        return this;
    }

    public CurrencyExchangeDataAssertion isCost(float cost) {
        Assertions.assertThat(actual.getCost()).isEqualTo(cost);
        return this;
    }

    public CurrencyExchangeDataAssertion isGain(float gain) {
        Assertions.assertThat(actual.getGain()).isEqualTo(gain);
        return this;
    }

    public CurrencyExchangeDataAssertion isIdAssign() {
        Assertions.assertThat(actual.getTransactionId()).isNotEqualTo(0L);
        return this;
    }

    public CurrencyExchangeDataAssertion hasCustomer(String customerId) {
        return hasCustomer(Long.parseLong(customerId));
    }

    public CurrencyExchangeDataAssertion hasCustomer(long customerId) {
        Assertions.assertThat(actual.getClientId()).isEqualTo(customerId);
        return this;
    }
}
