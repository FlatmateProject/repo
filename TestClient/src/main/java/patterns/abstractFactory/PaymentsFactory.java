package patterns.abstractFactory;


public abstract class PaymentsFactory {

	public abstract PaymentHandler createPaymentHandler();
	
	public abstract ConfirmationHandle createConfirmationHandler();

	public abstract CancelPaymentHandler createCancelPaymentHandler();
	
	public abstract CancelConfirmationHandler createCancelConfirmationHandler();
}
