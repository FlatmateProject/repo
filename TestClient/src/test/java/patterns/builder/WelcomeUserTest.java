package patterns.builder;

import org.junit.Test;

import patterns.builder.WelcomeUser.MessageConstructor;

import static org.junit.Assert.assertEquals;

public class WelcomeUserTest {

	@Test
	public void testShouldConstractPolishMessage() {
		new MessageConstructorTest() {
			
			@Override
			public MessageConstructor getConstructor() {
				return welcome.new PolishConstructor();
			}
			
			@Override
			public Object expectedInvitation() {
				return "Witaj";
			}
		}.execute();
	}

	@Test
	public void testShouldConstractEnglishMessage() {
		new MessageConstructorTest() {
			
			@Override
			public MessageConstructor getConstructor() {
				return welcome.new EnglishConstructor();
			}
			
			@Override
			public Object expectedInvitation() {
				return "Hello";
			}
		}.execute();
	}
	
	@Test
	public void testShouldConstractSpanishMessage() {
		new MessageConstructorTest() {
			
			@Override
			public MessageConstructor getConstructor() {
				return welcome.new SpanishConstructor();
			}
			
			@Override
			public Object expectedInvitation() {
				return "Hola";
			}
		}.execute();
	}
	
	private abstract class MessageConstructorTest {

		protected WelcomeUser welcome;

		public abstract Object expectedInvitation();

		public abstract MessageConstructor getConstructor();
		
		public String getUserName(){
			return "Piotro";
		}
		
		public void execute(){
			
			String userName = getUserName();
			
			welcome = new WelcomeUser();
			String result = welcome.constructMessage(getConstructor(), userName);
			
			assertEquals(expectedInvitation() + " " + getUserName(), result);
		}


	}

}
