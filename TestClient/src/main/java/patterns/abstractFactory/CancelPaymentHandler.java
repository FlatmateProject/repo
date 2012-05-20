package patterns.abstractFactory;

import patterns.abstractFactory.payments.PaymentsRequest;
import patterns.abstractFactory.payments.PaymentsResult;


public interface CancelPaymentHandler {

	PaymentsResult cancelPayment(PaymentsRequest request);
}
