package patterns.abstractFactory.publicity;

import patterns.abstractFactory.ConfirmationHandle;
import patterns.abstractFactory.model.PublicityEntity;
import patterns.abstractFactory.payments.PAYMENTS_STATUS;
import patterns.abstractFactory.payments.PaymentsRequest;
import patterns.abstractFactory.payments.PaymentsResult;

public class PublicityConfirmationHandler implements ConfirmationHandle {

	@Override
	public PaymentsResult executeConfirmation(PaymentsRequest request) {
		PublicityEntity publicity = (PublicityEntity) request.getObject();
		long amount = request.getAmount();
		PAYMENTS_STATUS status = publicity.work();
		return new PaymentsResult(publicity, status, amount);
	}

}
