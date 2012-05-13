package patterns;

public class Singleton {

	private static Singleton instace;

	private String message = "It works";

	private Singleton() {

	}

	public static Singleton getInstance() {
		if (instace == null) {
			instace = new Singleton();
		}
		return instace;
	}

	public String getMessage() {
		return message;
	}

}
