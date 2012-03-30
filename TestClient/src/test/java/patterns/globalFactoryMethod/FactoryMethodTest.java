package patterns.globalFactoryMethod;

public class FactoryMethodTest {

	private AbstractMessage message;
	
	private enum LANGUAGE {
		Polish,
		English,
		Spanish
	}
	
	
	
	public FactoryMethodTest(LANGUAGE language) {
		System.out.println(language.name());
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
	
	public void showMessage() {
		System.out.println(message.getMessage() +"\n");
	}

	private abstract class AbstractMessage {

		protected String message;

		abstract String append(String message);

		public String getMessage() {
			return message;
		}
	}
	
	private class PolishMessage extends AbstractMessage {

		public PolishMessage() {
			this.message = "Witaj świecie";
		}

		@Override
		public String append(String message) {
			this.message = "\"" + message + "\"";
			return this.message;
		}

	}
	
	private class EnglishMessage extends AbstractMessage {

		public EnglishMessage() {
			this.message = "Hello world";
		}

		@Override
		String append(String message) {
			this.message = "'" + message + "'";
			return this.message;
		}
	}
	
	private class SpanishMessage extends AbstractMessage {

		public SpanishMessage() {
			this.message = "Hola mundo";
		}

		@Override
		String append(String message) {
			this.message = "?" + message + "?";
			return this.message;
		}
	}
	
	public static void main(String[] args) {
		
		FactoryMethodTest test = null;
		
		test = new FactoryMethodTest(LANGUAGE.Polish);
		test.showMessage();

		test = new FactoryMethodTest(LANGUAGE.English);
		test.showMessage();
		
		test = new FactoryMethodTest(LANGUAGE.Spanish);
		test.showMessage();
		
	}

}
