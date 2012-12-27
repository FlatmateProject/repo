package exception;

import service.ERROR_MESSAGE;

import static service.ERROR_MESSAGE.HIBERNATE_ERROR;

;

public class DaoException extends MyException {

	private static final long serialVersionUID = 1L;
	
	public DaoException(ERROR_MESSAGE errorMessage) {
		super(errorMessage, null);
	}
	
	public DaoException(Exception e) {
		super(HIBERNATE_ERROR, e);
	}
	
	public DaoException(ERROR_MESSAGE errorMessage, Exception e) {
		super(errorMessage, e);
	}


}
