package patterns.abstractFactory.model;

import patterns.abstractFactory.payments.PAYMENTS_STATUS;

public abstract class ObjectEntity implements ModelActions {
	
	private long id;

	private OBJECT_STATUS status;

	private OBJECT_TYPE type;
	
	public ObjectEntity(OBJECT_TYPE type) {
		this(generateObjectId(), OBJECT_STATUS.NEW, type);
	}

	private ObjectEntity(long id, OBJECT_STATUS status, OBJECT_TYPE type) {
		this.id = id;
		this.status = status;
		this.type = type;
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
	
	public OBJECT_TYPE getType() {
		return type;
	}
	
	public PAYMENTS_STATUS pay() {
		payAction();
		status = OBJECT_STATUS.SET_TO_WORK;
		return PAYMENTS_STATUS.TODO;
	}

	public PAYMENTS_STATUS work() {
		workAction();
		status = OBJECT_STATUS.WORK;
		return PAYMENTS_STATUS.DONE;
	}

	public PAYMENTS_STATUS revert() {
		revertAction();
		status = OBJECT_STATUS.NEW;
		return PAYMENTS_STATUS.CANCELED;
	}
}
