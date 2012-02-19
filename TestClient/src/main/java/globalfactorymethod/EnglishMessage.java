package globalfactorymethod;

public class EnglishMessage extends AbstractMessage {

	public EnglishMessage() {
		this.message = "Hello world";
	}

	@Override
	String append(String message) {
		this.message = "'" + message + "'";
		return this.message;
	}
}
