package exception;

import java.io.Serializable;

public class ApplicationException extends Exception implements Serializable{

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
		super(e != null ? e.getMessage() : message);
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
