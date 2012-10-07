package patterns.localFactoryMethod;


import org.testng.annotations.Test;
import patterns.localfactorymethod.FactoryMethod;
import patterns.localfactorymethod.FactoryMethod.LANGUAGE;

import static org.testng.Assert.assertEquals;

public class LocalFactoryMethodTest {
	@Test
	public void testShowReturnPolishMessage() {
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
	public void testShowReturnEnglishMessage() {
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
	public void testShowReturnSpanishMessage() {
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

		public abstract String expectedMessage();

		public abstract LANGUAGE getLanguage();
		
		public String getUserName(){
			return "Piotro";
		}
		
		public void execute(){
			FactoryMethod result = FactoryMethod.getMethod(getLanguage());
			assertEquals(expectedMessage(), result.append(getUserName()));
		}
	}

}
