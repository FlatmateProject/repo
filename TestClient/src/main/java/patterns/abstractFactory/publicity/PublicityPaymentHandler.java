package patterns.abstractFactory.publicity;

import patterns.abstractFactory.PaymentHandler;
import patterns.abstractFactory.model.PublicityEntity;
import patterns.abstractFactory.payments.PAYMENTS_STATUS;
import patterns.abstractFactory.payments.PaymentsRequest;
import patterns.abstractFactory.payments.PaymentsResult;

public class PublicityPaymentHandler implements PaymentHandler {

	@Override
	public PaymentsResult executePayment(PaymentsRequest request) {
		PublicityEntity publicity = (PublicityEntity) request.getObject();
		long amount = request.getAmount();
		PAYMENTS_STATUS status = publicity.pay();
		return new PaymentsResult(publicity, status, amount);
	}

}
