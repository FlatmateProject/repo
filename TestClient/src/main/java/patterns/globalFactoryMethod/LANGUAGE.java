package patterns.globalFactoryMethod;


public enum LANGUAGE {
	
	Polish("Polish", PolishMessage.class),
	English("English", EnglishMessage.class),
	Spanish("Spanish", SpanishMessage.class)
	;

	private final String name;
	private final Class<? extends AbstractMessage> classMessage;

	LANGUAGE(String name, Class<? extends AbstractMessage> classMessage) {
		this.name = name;
		this.classMessage = classMessage;
	}


	public AbstractMessage getInstance() {
		try {
			return classMessage.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}

    @Override
    public String toString(){
        return name;
    }
}

