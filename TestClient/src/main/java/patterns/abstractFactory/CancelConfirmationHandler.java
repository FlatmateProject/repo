package patterns.abstractFactory;

import patterns.abstractFactory.payments.PaymentsRequest;
import patterns.abstractFactory.payments.PaymentsResult;


public interface CancelConfirmationHandler {

	PaymentsResult cancelConfirmation(PaymentsRequest request);
}
