package exception;

public class ServiceException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String message;

	private Exception parentException;
	
	public ServiceException(String message) {
		this(message, null);
	}
	
	public ServiceException(Exception e) {
		this(null, e);
	}
	
	public ServiceException(String message, Exception e) {
		super(message +" : "+ (e != null ? e.getMessage() : ""));
		this.message = message;
		this.parentException = e; 
		if (e != null) {
			e.printStackTrace();
		}
	}
	
	public String getErrorMessage() {
		return message;
	}

	public Exception getParentException() {
		return parentException;
	}
	
}
