package patterns.abstractFactory.advert;

import patterns.abstractFactory.PaymentHandler;
import patterns.abstractFactory.model.AdvertEntity;
import patterns.abstractFactory.payments.PAYMENTS_STATUS;
import patterns.abstractFactory.payments.PaymentsRequest;
import patterns.abstractFactory.payments.PaymentsResult;

public class AdvertPaymentHandler implements PaymentHandler {

	@Override
	public PaymentsResult executePayment(PaymentsRequest request) {
		AdvertEntity advert = (AdvertEntity) request.getObject();
		PAYMENTS_STATUS status = advert.pay();
		return new PaymentsResult(advert, status);
	}


}
