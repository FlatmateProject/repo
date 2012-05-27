package patterns.abstractFactory.payments;

import patterns.abstractFactory.model.ObjectEntity;

public class PaymentsResult {
	
	private ObjectEntity object;
	
	private PAYMENTS_STATUS paymentStatus;
	
	private long amount;
	
	public PaymentsResult(ObjectEntity object, PAYMENTS_STATUS status, long amount) {
		this.object = object;
		this.paymentStatus = status;
		this.amount = amount;
	}
	
	public ObjectEntity getObject(){
		return object;
	}

	public PAYMENTS_STATUS getPaymentStatus() {
		return paymentStatus;
	}

	public long getAmount() {
		return amount;
	}
}