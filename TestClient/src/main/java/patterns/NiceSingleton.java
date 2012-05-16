package patterns;

public class NiceSingleton {

	private final static NiceSingleton instace = buildComplexObject();

	private String message = "It works and it is really nice";

	private NiceSingleton() {

	}

	private static NiceSingleton buildComplexObject(){
		NiceSingleton instance = new NiceSingleton();
		//do someThing
		return instance;
	}
	
	public static NiceSingleton getInstance() {
		return instace;
	}

	public String getMessage() {
		return message;
	}

}
