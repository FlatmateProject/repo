package patterns.localFactoryMethod;

import org.junit.Test;

import patterns.builder.WelcomeUserConstructor;
import patterns.localfactorymethod.FactoryMethod;
import patterns.localfactorymethod.FactoryMethod.LANGUAGE;

import static org.junit.Assert.assertEquals;

public class LocalFactoryMethodTest {
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
			FactoryMethod result = new FactoryMethod(getLanguage());
			assertEquals(expectedMessage(), result.append(getUserName()));
		}
	}

}
