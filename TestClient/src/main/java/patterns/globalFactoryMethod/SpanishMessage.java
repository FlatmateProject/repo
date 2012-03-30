package patterns.globalFactoryMethod;


public class SpanishMessage extends AbstractMessage {

	public SpanishMessage() {
		this.message = "Hola mundo";
	}

	@Override
	public String append(String message) {
		this.message += " ?" + message + "?";
		return this.message;
	}
}
