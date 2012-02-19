package globalfactorymethod;

public abstract class AbstractMessage {

	protected String message;

	abstract String append(String message);

	public String getMessage() {
		return message;
	}
}