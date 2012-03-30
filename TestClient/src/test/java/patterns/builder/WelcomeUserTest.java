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
			public String expectedInvocation() {
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
			public String expectedInvocation() {
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
			public String expectedInvocation() {
				return "Hola";
			}
		}.execute();
	}
	
	private abstract class MessageConstructorTest {

		protected WelcomeUser welcome;

		public abstract String expectedInvocation();

		public abstract MessageConstructor getConstructor();
		
		public String getUserName(){
			return "Piotro";
		}
		
		public void execute(){
			
			welcome = new WelcomeUser();
			String result = welcome.constructMessage(getConstructor(), getUserName());
			
			assertEquals(expectedInvocation() +" " + getUserName(), result);
		}
	}

}
