package exception;

public class ApplicationException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String message;

	private Exception parentException;
	
	public ApplicationException(String message) {
		this(message, null);
	}
	
	public ApplicationException(Exception e) {
		this(e.getMessage(), e);
	}
	
	public ApplicationException(String message, Exception e) {
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
