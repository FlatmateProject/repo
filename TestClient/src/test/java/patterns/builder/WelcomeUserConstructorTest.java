package patterns.builder;

import org.junit.Test;

import patterns.builder.WelcomeUserConstructor.AbstractMessageBuilder;

import static org.junit.Assert.assertEquals;

public class WelcomeUserConstructorTest {

	@Test
	public void testShouldConstractPolishMessage() {
		new MessageConstructorTest() {
			
			@Override
			public AbstractMessageBuilder getBuilder() {
				return constructor.new PolishMessageBuilder();
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
			public AbstractMessageBuilder getBuilder() {
				return constructor.new EnglishMessageBuilder();
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
			public AbstractMessageBuilder getBuilder() {
				return constructor.new SpanishMessageBuilder();
			}
			
			@Override
			public String expectedInvocation() {
				return "Hola";
			}
		}.execute();
	}
	
	private abstract class MessageConstructorTest {

		protected WelcomeUserConstructor constructor;

		public abstract String expectedInvocation();

		public abstract AbstractMessageBuilder getBuilder();
		
		public String getUserName(){
			return "Piotro";
		}
		
		public void execute(){
			
			constructor = new WelcomeUserConstructor();
			String result = constructor.constructMessage(getBuilder(), getUserName());
			
			assertEquals(expectedInvocation() + " " + getUserName(), result);
		}
	}

}