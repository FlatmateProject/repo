package patterns.builder;


public class WelcomeUser {
	

	public abstract class MessageConstructor {

		protected String message;
		
		public abstract void appendUserName(String userName);

		public String resultMessage() {
			return message;
		}
	}
	
	public class PolishConstructor extends MessageConstructor {

		@Override
		public void appendUserName(String userName) {
			this.message = "Witaj " + userName;
		}

	}
	
	public class EnglishConstructor extends MessageConstructor {

		@Override
		public void appendUserName(String userName) {
			this.message = "Hello " + userName;
		}
	}
	
	public class SpanishConstructor extends MessageConstructor {
		
		@Override
		public void appendUserName(String userName) {
			this.message = "Hola " + userName;
		}
	}
	
	public String constructMessage(MessageConstructor constructor, String userName) {
		constructor.appendUserName(userName);
		return constructor.resultMessage(); 
	}
	
}
