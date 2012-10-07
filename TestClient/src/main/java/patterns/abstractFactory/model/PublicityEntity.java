package patterns.abstractFactory.model;

public class PublicityEntity extends ObjectEntity {

	public PublicityEntity() {
		super(OBJECT_TYPE.PUBLICITY);
	}

	@Override
	public void payAction() {
		System.out.println("Publicity has been paid");
	}

	@Override
	public void workAction() {
		System.out.println("Publicity is worked");
	}

	@Override
	public void revertAction() {
		System.out.println("Publicity has been reverted to new");
	}

}
