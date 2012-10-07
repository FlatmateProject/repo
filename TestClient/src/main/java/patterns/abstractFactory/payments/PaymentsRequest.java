package patterns.abstractFactory.payments;

import patterns.abstractFactory.model.ObjectEntity;

public class PaymentsRequest {
	
	private ObjectEntity object;

	private long amount;
	
	public PaymentsRequest(ObjectEntity object, long amount) {
		this.object = object;
		this.amount = amount;
	}

	public ObjectEntity getObject() {
		return object;
	}

	public long getAmount() {
		return amount;
	}
}
