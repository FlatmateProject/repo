package exception;

public class DaoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	private Exception parentException;

	public DaoException(Exception e) {
		this(null, e);
	}
	
	public DaoException(String message, Exception e) {
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
