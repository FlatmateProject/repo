package exception;

public class DaoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	private Exception parentException;

	public DaoException(Exception e) {
		this(e.getMessage(), e);
	}
	
	public DaoException(String message, Exception e) {
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
