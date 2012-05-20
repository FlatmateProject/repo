package patterns.abstractFactory.advert;

import patterns.abstractFactory.CancelConfirmationHandler;
import patterns.abstractFactory.model.AdvertEntity;
import patterns.abstractFactory.payments.PAYMENTS_STATUS;
import patterns.abstractFactory.payments.PaymentsRequest;
import patterns.abstractFactory.payments.PaymentsResult;

public class AdvertCancelConfirmationHandler implements
		CancelConfirmationHandler {

	@Override
	public PaymentsResult cancelConfirmation(PaymentsRequest request) {
		AdvertEntity advert = (AdvertEntity) request.getObject();
		long amount = request.getAmount();
		PAYMENTS_STATUS status = advert.revert();
		return new PaymentsResult(advert, status, amount);
	}

}
