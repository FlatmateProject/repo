package patterns.abstractFactory.advert;

import patterns.abstractFactory.*;

public class AdvertPaymentsFactory extends PaymentsFactory{

	@Override
	public PaymentHandler createPaymentHandler() {
		return new AdvertPaymentHandler();
	}

	@Override
	public ConfirmationHandle createConfirmationHandler() {
		return new AdvertConfirmationHandler();
	}

	@Override
	public CancelPaymentHandler createCancelPaymentHandler() {
		return new AdvertCancelPaymentHandler();
	}

	@Override
	public CancelConfirmationHandler createCancelConfirmationHandler() {
		return new AdvertCancelConfirmationHandler();
	}


}
