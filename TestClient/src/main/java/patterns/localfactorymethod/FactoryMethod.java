package patterns.localfactorymethod;

public class FactoryMethod {

	private AbstractMessage message;
	
	public enum LANGUAGE {
		Polish,
		English,
		Spanish
	}
	
	public FactoryMethod(LANGUAGE language) {
		message = createMessage(language);
	}
	
	private AbstractMessage createMessage(LANGUAGE language) {
		switch (language) {
			case Polish:  return new PolishMessage();
			case English: return new EnglishMessage();
			case Spanish: return new SpanishMessage();
		}
		;

		return null;
	}
	
	public String append(String message) {
		return this.message.append(message);
	}

	private abstract class AbstractMessage {

		protected String message;

		abstract public String append(String message);
	}
	
	private class PolishMessage extends AbstractMessage {

		public PolishMessage() {
			this.message = "Witaj świecie";
		}

		@Override
		public String append(String message) {
			this.message += " \"" + message + "\"";
			return this.message;
		}

	}
	
	private class EnglishMessage extends AbstractMessage {

		public EnglishMessage() {
			this.message = "Hello world";
		}

		@Override
		public String append(String message) {
			this.message += " '" + message + "'";
			return this.message;
		}
	}
	
	private class SpanishMessage extends AbstractMessage {

		public SpanishMessage() {
			this.message = "Hola mundo";
		}

		@Override
		public String append(String message) {
			this.message += " ?" + message + "?";
			return this.message;
		}
	}
}
