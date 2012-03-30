package patterns.globalFactoryMethod;

public class EnglishMessage extends AbstractMessage {

	public EnglishMessage() {
		this.message = "Hello world";
	}

	@Override
	public String append(String message) {
		this.message += " '" + message + "'";
		return this.message;
	}
}
