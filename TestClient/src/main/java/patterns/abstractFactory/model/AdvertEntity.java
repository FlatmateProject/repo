package patterns.abstractFactory.model;


public class AdvertEntity extends ObjectEntity {

	public AdvertEntity() {
		super(OBJECT_TYPE.ADVERT);
	}

	@Override
	public void payAction() {
		System.out.println("Advert has beed paid");
	}

	@Override
	public void workAction() {
		System.out.println("Advert is worked");
	}

	@Override
	public void revertAction() {
		System.out.println("Advert has beed reverted to new");
	}

}
