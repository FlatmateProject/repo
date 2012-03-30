package patterns.globalFactoryMethod;

public class PolishMessage extends AbstractMessage {

	public PolishMessage() {
		this.message = "Witaj świecie";
	}

	@Override
	public String append(String message) {
		this.message += " \"" + message + "\"";
		return this.message;
	}

}