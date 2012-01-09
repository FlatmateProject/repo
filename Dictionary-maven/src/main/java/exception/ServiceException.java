package exception;

public class ServiceException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String message;

	private Exception parentException;
	
	public ServiceException(String message) {
		super(message);
		this.message = message;
	}
	
	public ServiceException(Exception e) {
		super((e != null ? e.getMessage() : ""));
		this.parentException = e; 
		printStackTraceIfAvailable(e);
	}
	
	public ServiceException(String message, Exception e) {
		super(message +" : "+ (e != null ? e.getMessage() : ""));
		this.message = message;
		this.parentException = e; 
		printStackTraceIfAvailable(e);
	}
	
	private void printStackTraceIfAvailable(Exception e) {
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
