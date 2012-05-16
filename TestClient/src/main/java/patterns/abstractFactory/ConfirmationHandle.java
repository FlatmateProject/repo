package patterns.abstractFactory;

import patterns.abstractFactory.payments.PaymentsRequest;
import patterns.abstractFactory.payments.PaymentsResult;

public interface ConfirmationHandle {

	PaymentsResult executeConfirmation(PaymentsRequest request);
}
