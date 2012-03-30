package patterns.globalFactoryMethod;


public enum LANGUAGE {
	
	Polish("Polish", PolishMessage.class),
	English("English", EnglishMessage.class),
	Spanish("Spanish", SpanishMessage.class)
	;

	private String name;
	private Class<? extends AbstractMessage> classMessage;

	LANGUAGE(String name, Class<? extends AbstractMessage> classMessage) {
		this.name = name;
		this.classMessage = classMessage;
	}

	public String getName() {
		return name;
	}

	public AbstractMessage getInstance() {
		try {
			return (AbstractMessage)classMessage.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}

}

