package patterns.abstractFactory.advert;

import patterns.abstractFactory.CancelPaymentHandler;
import patterns.abstractFactory.model.AdvertEntity;
import patterns.abstractFactory.payments.PAYMENTS_STATUS;
import patterns.abstractFactory.payments.PaymentsRequest;
import patterns.abstractFactory.payments.PaymentsResult;

public class AdvertCancelPaymentHandler implements CancelPaymentHandler {

	@Override
	public PaymentsResult cancelPayment(PaymentsRequest request) {
		AdvertEntity advert = (AdvertEntity) request.getObject();
		long amount = request.getAmount();
		PAYMENTS_STATUS status = advert.revert();
		return new PaymentsResult(advert, status, amount);
	}

}
