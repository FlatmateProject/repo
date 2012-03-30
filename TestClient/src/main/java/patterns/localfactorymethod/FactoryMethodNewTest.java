package localfactorymethod;

public class FactoryMethodNewTest {

	public AbstractMessage message;
	
	public enum LANGUAGE {
		
		Polish("Polish", PolishMessage.class),
		English("English", EnglishMessage.class),
		Spanish("Spanish", SpanishMessage.class)
		;

		private String name;
		private Class<? extends AbstractMessage> classMessage;

		LANGUAGE(String name, Class<? extends AbstractMessage> classMessage) {
			this.name = name;
			this.classMessage = classMessage;
		}

		public String getName() {
			return name;
		}

		public AbstractMessage getInstance() {
			try {
				return (AbstractMessage)classMessage.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			} 
			return null;
		}

	}
	
	
	
	public FactoryMethodNewTest(LANGUAGE language) {
		System.out.println(language.name());
		message = createMessageNew(language);
	}
	
	public AbstractMessage createMessageNew(LANGUAGE language) {
		return language.getInstance();
	}
	
	public void showMessage() {
		System.out.println(message.getMessage() +"\n");
	}

	public abstract class AbstractMessage {

		protected String message;

		abstract String append(String message);

		public String getMessage() {
			return message;
		}
	}
	
	public class PolishMessage extends AbstractMessage {

		public PolishMessage() {
			this.message = "Witaj świecie";
		}

		@Override
		public String append(String message) {
			this.message = "\"" + message + "\"";
			return this.message;
		}

	}
	
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
	
	public static void main(String[] args) {
		
		FactoryMethodNewTest test = null;
		
		test = new FactoryMethodNewTest(LANGUAGE.Polish);
		test.showMessage();

		test = new FactoryMethodNewTest(LANGUAGE.English);
		test.showMessage();
		
		test = new FactoryMethodNewTest(LANGUAGE.Spanish);
		test.showMessage();
		
	}

}
