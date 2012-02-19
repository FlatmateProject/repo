//package patterns;
//
//public class WelcomeUser {
//	
//	private enum LANGUAGE {
//		Polish
//		English
//		Spanish(new SpanishConstructor());
//		
//		private MessageConstructor constructor;
//
//		LANGUAGE(MessageConstructor constructor) {
//			this.constructor = constructor;
//		}
//
//		public MessageConstructor getConstructor() {
//			return constructor;
//		}	
//	}
//	
//	
//	private enum NUMBER {
//		Polish(new Long(0)),
//		English(new Long(1)),
//		Spanish(new Long(2));
//		
//		private Long constructor;
//
//		NUMBER(Long constructor) {
//			this.constructor = constructor;
//		}
//
//		public Long getConstructor() {
//			return constructor;
//		}	
//	}
//	
//	private class MessageConstructor {
//
//		protected String message;
//
//		public void appendUserName(String userName){}
//
//		public String resultMessage() {
//			return message;
//		}
//	}
//	
//	private class PolishConstructor extends MessageConstructor {
//
//		@Override
//		public void appendUserName(String userName) {
//			this.message = "Witaj " + userName;
//		}
//
//	}
//	
//	private class EnglishConstructor extends MessageConstructor {
//
//		@Override
//		public void appendUserName(String userName) {
//			this.message = "Hello " + userName;
//		}
//	}
//	
//	private class SpanishConstructor extends MessageConstructor {
//		
//		@Override
//		public void appendUserName(String userName) {
//			this.message = "Hola " + userName;
//		}
//	}
//	
//	private String constructMessage(MessageConstructor constructor, String userName) {
//		constructor.appendUserName(userName);
//		return constructor.resultMessage(); 
//	}
//	
//	private void showMessage(String message) {
//		System.out.println(message);
//	}
//	
//	public void test() {
//		
//		LANGUAGE type = LANGUAGE.English;
//		
//		type.getConstructor();
//		
//		String welcomeMessage = null;
//		
//		String userName = "Piotro";
//		
//		welcomeMessage = constructMessage(new PolishConstructor(), userName);
//		showMessage(welcomeMessage);
//
//		welcomeMessage = constructMessage(new EnglishConstructor(), userName);
//		showMessage(welcomeMessage);
//		
//		welcomeMessage = constructMessage(new SpanishConstructor(), userName);
//		showMessage(welcomeMessage);
//	}
//	
//	public static void main(String[] args) {
//		WelcomeUser welcome = new WelcomeUser();
//		welcome.test();
//		
//	}
//
//
//
//}
