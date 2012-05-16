package patterns.abstractFactory.advert;

import patterns.abstractFactory.ConfirmationHandle;
import patterns.abstractFactory.model.AdvertEntity;
import patterns.abstractFactory.payments.PAYMENTS_STATUS;
import patterns.abstractFactory.payments.PaymentsRequest;
import patterns.abstractFactory.payments.PaymentsResult;

public class AdvertConfirmationHandler implements ConfirmationHandle {

	@Override
	public PaymentsResult executeConfirmation(PaymentsRequest request) {
		AdvertEntity advert = (AdvertEntity) request.getObject();
		PAYMENTS_STATUS status = advert.work();
		return new PaymentsResult(advert, status);
	}

}
