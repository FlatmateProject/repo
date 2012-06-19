package patterns.localfactorymethod;

public abstract class AbstractMessage {

	protected String message;

	abstract public String append(String message);
}