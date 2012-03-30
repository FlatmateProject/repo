package patterns.globalFactoryMethod;

public class FactoryMethodTest {

	private AbstractMessage message;
	
	public FactoryMethodTest(LANGUAGE language){
		System.out.println(language.getName());
		message = createMessageNewApproach(language);
	}
	
	private AbstractMessage createMessageNewApproach(LANGUAGE language) {
		return language.getInstance();
	}
	
	public void showMessage() {
		System.out.println(message.getMessage() +"\n");
	}

	public static void main(String[] args) {
		
		FactoryMethodTest test = null;
		
		test = new FactoryMethodTest(LANGUAGE.Polish);
		test.showMessage();

		test = new FactoryMethodTest(LANGUAGE.English);
		test.showMessage();
		
		test = new FactoryMethodTest(LANGUAGE.Spanish);
		test.showMessage();
		
	}

}
