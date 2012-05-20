package exception;

import service.ERROR_MESSAGE;

public abstract class MyException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private ERROR_MESSAGE errorMessage;

	private Exception parentException;

	public MyException(ERROR_MESSAGE errorMessage, Exception e) {
		super((e != null ? e.getMessage() : errorMessage.getMessage()));
		this.errorMessage = errorMessage;
		this.parentException = e; 
		if (e != null) {
			errorMessage.setMessage(e.getMessage());
			e.printStackTrace();
	}
//		else{
//			errorMessage.setMessage(this.getMessage());
//			this.printStackTrace();
//		}
	}
	
	public ERROR_MESSAGE getErrorMessage() {
		return errorMessage;
	}

	public Exception getParentException() {
		return parentException;
	}
	
}
