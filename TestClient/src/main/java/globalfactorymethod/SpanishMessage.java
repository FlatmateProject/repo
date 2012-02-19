package globalfactorymethod;


public class SpanishMessage extends AbstractMessage {

	public SpanishMessage() {
		this.message = "Hola mundo";
	}

	@Override
	String append(String message) {
		this.message = "?" + message + "?";
		return this.message;
	}
}
