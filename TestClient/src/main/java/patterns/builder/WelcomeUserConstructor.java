package patterns.builder;


public class WelcomeUserConstructor {
	

	public abstract class AbstractMessageBuilder {

		protected String message;
		
		public abstract void appendUserName(String userName);

		public String resultMessage() {
			return message;
		}
	}
	
	public class PolishMessageBuilder extends AbstractMessageBuilder {

		@Override
		public void appendUserName(String userName) {
			this.message = "Witaj " + userName;
		}

	}
	
	public class EnglishMessageBuilder extends AbstractMessageBuilder {

		@Override
		public void appendUserName(String userName) {
			this.message = "Hello " + userName;
		}
	}
	
	public class SpanishMessageBuilder extends AbstractMessageBuilder {
		
		@Override
		public void appendUserName(String userName) {
			this.message = "Hola " + userName;
		}
	}
	
	public String constructMessage(AbstractMessageBuilder constructor, String userName) {
		constructor.appendUserName(userName);
		return constructor.resultMessage(); 
	}
	
}
