package patterns;

public class NiceSingleton {

	private final static NiceSingleton instance = buildComplexObject();

	private String message = "It works and it is really nice";

	private NiceSingleton() {

	}

	private static NiceSingleton buildComplexObject(){
		return  new NiceSingleton();
	}
	
	public static NiceSingleton getInstance() {
		return instance;
	}

	public String getMessage() {
		return message;
	}

}
