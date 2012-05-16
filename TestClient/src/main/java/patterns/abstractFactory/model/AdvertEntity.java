package patterns.abstractFactory.model;

import patterns.abstractFactory.payments.PAYMENTS_STATUS;

public class AdvertEntity extends ObjectEntity {

	private long id;

	private OBJECT_STATUS status;

	public AdvertEntity() {
		this(generateObjectId(), OBJECT_STATUS.NEW);
	}

	private AdvertEntity(long id, OBJECT_STATUS status) {
		this.id = id;
		this.status = status;
	}

	private static long generateObjectId() {
		return Math.round(Math.random() * 100);
	}

	public long getId() {
		return id;
	}

	public OBJECT_STATUS getStatus() {
		return status;
	}
	
	public PAYMENTS_STATUS pay() {
		status = OBJECT_STATUS.SET_TO_WORK;
		return PAYMENTS_STATUS.TODO;
	}


	public PAYMENTS_STATUS work() {
		status = OBJECT_STATUS.WORK;
		return PAYMENTS_STATUS.DONE;
	}

}
