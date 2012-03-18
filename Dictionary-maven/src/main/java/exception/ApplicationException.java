package exception;

import static service.ERROR_MESSAGE.EXECUTE_SERVICE_ERROR;

import java.io.Serializable;

import service.ERROR_MESSAGE;

public class ApplicationException extends MyException implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public ApplicationException(ERROR_MESSAGE errorMessage) {
		this(errorMessage, null);
	}
	
	public ApplicationException(Exception e) {
		super(EXECUTE_SERVICE_ERROR, e, e.getMessage());
	}
	
	public ApplicationException(ERROR_MESSAGE errorMessage, Exception e) {
		super(errorMessage, e);

	}

}
