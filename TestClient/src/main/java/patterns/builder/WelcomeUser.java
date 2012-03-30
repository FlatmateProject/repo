package patterns.builder;

public class WelcomeUser {
	

	private abstract class MessageConstructor {

		protected String message;

		public abstract void appendUserName(String userName);

		public String resultMessage() {
			return message;
		}
	}
	
	private class PolishConstructor extends MessageConstructor {

		@Override
		public void appendUserName(String userName) {
			this.message = "Witaj " + userName;
		}

	}
	
	private class EnglishConstructor extends MessageConstructor {

		@Override
		public void appendUserName(String userName) {
			this.message = "Hello " + userName;
		}
	}
	
	private class SpanishConstructor extends MessageConstructor {
		
		@Override
		public void appendUserName(String userName) {
			this.message = "Hola " + userName;
		}
	}
	
	private String constructMessage(MessageConstructor constructor, String userName) {
		constructor.appendUserName(userName);
		return constructor.resultMessage(); 
	}
	
	private void showMessage(String message) {
		System.out.println(message);
	}
	
	public void test() {

		String welcomeMessage = null;
		
		String userName = "Piotro";
		
		welcomeMessage = constructMessage(new PolishConstructor(), userName);
		showMessage(welcomeMessage);

		welcomeMessage = constructMessage(new EnglishConstructor(), userName);
		showMessage(welcomeMessage);
		
		welcomeMessage = constructMessage(new SpanishConstructor(), userName);
		showMessage(welcomeMessage);
	}
	
	public static void main(String[] args) {
		WelcomeUser welcome = new WelcomeUser();
		welcome.test();
		
	}



}
