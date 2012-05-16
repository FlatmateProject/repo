package patterns.abstractFactory.advert;

import patterns.abstractFactory.ConfirmationHandle;
import patterns.abstractFactory.PaymentHandler;
import patterns.abstractFactory.PaymentsFactory;

public class AdvertPaymentsFactory extends PaymentsFactory{

	@Override
	public PaymentHandler createPaymentHandler() {
		return new AdvertPaymentHandler();
	}

	@Override
	public ConfirmationHandle createConfirmationHandler() {
		return new AdvertConfirmationHandler();
	}


}
