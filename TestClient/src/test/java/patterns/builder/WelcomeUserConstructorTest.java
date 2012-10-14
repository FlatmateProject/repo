package patterns.builder;

import org.testng.annotations.Test;
import patterns.builder.WelcomeUserConstructor.AbstractMessageBuilder;

import static org.fest.assertions.Assertions.assertThat;

public class WelcomeUserConstructorTest {

	@Test
	public void testShouldConstructPolishMessage() {
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
	public void testShouldConstructEnglishMessage() {
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
	public void testShouldConstructSpanishMessage() {
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
		
		public String userName = "Piotro";

		public void execute(){
			 //given
			constructor = new WelcomeUserConstructor();

            //when
			String result = constructor.constructMessage(getBuilder(), userName);

            //then
            assertThat(result).isNotNull().isEqualTo(expectedMessage());
		}

        private String expectedMessage() {
            return expectedInvocation() + " " + userName;
        }
    }

}
