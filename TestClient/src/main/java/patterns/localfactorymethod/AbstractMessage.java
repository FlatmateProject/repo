package patterns.localfactorymethod;

public abstract class AbstractMessage {

	protected String message;

	abstract public String append(String message);

    public AbstractMessage(String message){
        this.message = message;
    }

}