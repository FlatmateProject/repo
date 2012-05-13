package patterns.builder;


public class WelcomeUserConstructor {
	

	public abstract class MessageBuilder {

		protected String message;
		
		public abstract void appendUserName(String userName);

		public String resultMessage() {
			return message;
		}
	}
	
	public class PolishBuilder extends MessageBuilder {

		@Override
		public void appendUserName(String userName) {
			this.message = "Witaj " + userName;
		}

	}
	
	public class EnglishBuilder extends MessageBuilder {

		@Override
		public void appendUserName(String userName) {
			this.message = "Hello " + userName;
		}
	}
	
	public class SpanishBuilder extends MessageBuilder {
		
		@Override
		public void appendUserName(String userName) {
			this.message = "Hola " + userName;
		}
	}
	
	public String constructMessage(MessageBuilder constructor, String userName) {
		constructor.appendUserName(userName);
		return constructor.resultMessage(); 
	}
	
}
