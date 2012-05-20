package patterns.abstractFactory.publicity;

import patterns.abstractFactory.CancelConfirmationHandler;
import patterns.abstractFactory.CancelPaymentHandler;
import patterns.abstractFactory.ConfirmationHandle;
import patterns.abstractFactory.PaymentHandler;
import patterns.abstractFactory.PaymentsFactory;

public class PublicityPaymentsFactory extends PaymentsFactory {

	@Override
	public PaymentHandler createPaymentHandler() {
		return new PublicityPaymentHandler();
	}

	@Override
	public ConfirmationHandle createConfirmationHandler() {
		return new PublicityConfirmationHandler();
	}

	@Override
	public CancelPaymentHandler createCancelPaymentHandler() {
		return new PublicityCancelPaymentHandler();
	}

	@Override
	public CancelConfirmationHandler createCancelConfirmationHandler() {
		return new PublicityCancelConfirmationHandler();
	}

}
