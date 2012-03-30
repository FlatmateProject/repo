package patterns.globalFactoryMethod;

public abstract class AbstractMessage {

	protected String message;

	abstract public String append(String message);

	public String getMessage() {
		return message;
	}
}