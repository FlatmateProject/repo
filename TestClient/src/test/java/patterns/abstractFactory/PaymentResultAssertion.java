package patterns.abstractFactory;

import org.fest.assertions.Assertions;
import org.fest.assertions.GenericAssert;
import patterns.abstractFactory.model.OBJECT_STATUS;
import patterns.abstractFactory.payments.PAYMENTS_STATUS;
import patterns.abstractFactory.payments.PaymentsResult;

public class PaymentResultAssertion extends GenericAssert<PaymentResultAssertion, PaymentsResult> {

    private PaymentResultAssertion(Class<PaymentResultAssertion> selfType, PaymentsResult actual) {
        super(selfType, actual);
    }

    public static PaymentResultAssertion assertThat(PaymentsResult actual){
        return new PaymentResultAssertion(PaymentResultAssertion.class, actual);
    }

    public PaymentResultAssertion isPaymentStatus(PAYMENTS_STATUS status){
        Assertions.assertThat(actual.getPaymentStatus()).isEqualTo(status);
        return this;
    }

    public PaymentResultAssertion isAmount(long amount){
        Assertions.assertThat(actual.getAmount()).isEqualTo(amount);
        return this;
    }

    public PaymentResultAssertion isObjectId(long objectId) {
        Assertions.assertThat(actual.getObject().getId()).isEqualTo(objectId);
        return this;
    }

    public PaymentResultAssertion isObjectStatus(OBJECT_STATUS status) {
        Assertions.assertThat(actual.getObject().getStatus()).isEqualTo(status);
        return this;
    }
}
