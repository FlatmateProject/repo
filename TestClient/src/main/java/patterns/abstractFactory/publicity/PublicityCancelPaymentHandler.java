package patterns.abstractFactory.publicity;

import patterns.abstractFactory.CancelPaymentHandler;
import patterns.abstractFactory.model.PublicityEntity;
import patterns.abstractFactory.payments.PAYMENTS_STATUS;
import patterns.abstractFactory.payments.PaymentsRequest;
import patterns.abstractFactory.payments.PaymentsResult;

public class PublicityCancelPaymentHandler implements CancelPaymentHandler {

	@Override
	public PaymentsResult cancelPayment(PaymentsRequest request) {
		PublicityEntity publicity = (PublicityEntity) request.getObject();
		long amount = request.getAmount();
		PAYMENTS_STATUS status = publicity.revert();
		return new PaymentsResult(publicity, status, amount);
	}

}
