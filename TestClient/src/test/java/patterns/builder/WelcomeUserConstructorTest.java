package patterns.builder;

import org.junit.Test;

import patterns.builder.WelcomeUserConstructor.MessageBuilder;

import static org.junit.Assert.assertEquals;

public class WelcomeUserConstructorTest {

	@Test
	public void testShouldConstractPolishMessage() {
		new MessageConstructorTest() {
			
			@Override
			public MessageBuilder getConstructor() {
				return welcome.new PolishBuilder();
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
			public MessageBuilder getConstructor() {
				return welcome.new EnglishBuilder();
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
			public MessageBuilder getConstructor() {
				return welcome.new SpanishBuilder();
			}
			
			@Override
			public String expectedInvocation() {
				return "Hola";
			}
		}.execute();
	}
	
	private abstract class MessageConstructorTest {

		protected WelcomeUserConstructor welcome;

		public abstract String expectedInvocation();

		public abstract MessageBuilder getConstructor();
		
		public String getUserName(){
			return "Piotro";
		}
		
		public void execute(){
			
			welcome = new WelcomeUserConstructor();
			String result = welcome.constructMessage(getConstructor(), getUserName());
			
			assertEquals(expectedInvocation() +" " + getUserName(), result);
		}
	}

}
