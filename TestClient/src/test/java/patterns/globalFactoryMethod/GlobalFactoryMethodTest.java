package patterns.globalFactoryMethod;

import org.junit.Test;

import patterns.builder.WelcomeUserConstructor;

import static org.junit.Assert.assertEquals;

public class GlobalFactoryMethodTest {

	@Test
	public void testShouldReturnPolishMessage() {
		new MessageTest() {
			
			@Override
			public LANGUAGE getLanguage() {
				return LANGUAGE.Polish;
			}
			
			@Override
			public String expectedMessage() {
				return "Witaj świecie \"Piotro\"";
			}
		}.execute();
	}

	@Test
	public void testShouldReturnEnglishMessage() {
		new MessageTest() {
			
			@Override
			public LANGUAGE getLanguage() {
				return LANGUAGE.English;
			}
			
			@Override
			public String expectedMessage() {
				return "Hello world 'Piotro'";
			}
		}.execute();
	}
	
	@Test
	public void testShouldReturnSpanishMessage() {
		new MessageTest() {
			
			@Override
			public LANGUAGE getLanguage() {
				return LANGUAGE.Spanish;
			}
			
			@Override
			public String expectedMessage() {
				return "Hola mundo ?Piotro?";
			}
		}.execute();
	}
	
	private abstract class MessageTest {

		protected WelcomeUserConstructor welcome;

		public abstract String expectedMessage();

		public abstract LANGUAGE getLanguage();
		
		public String getUserName(){
			return "Piotro";
		}
		
		public void execute(){
			AbstractMessage result = getLanguage().getInstance();
			assertEquals(expectedMessage(), result.append(getUserName()));
		}
	}

}
