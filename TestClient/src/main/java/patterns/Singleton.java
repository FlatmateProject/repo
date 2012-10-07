package patterns;

public class Singleton {

	private static Singleton instance;

	private String message = "It works";

	private Singleton() {

	}

	public static Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}

	public String getMessage() {
		return message;
	}

}
