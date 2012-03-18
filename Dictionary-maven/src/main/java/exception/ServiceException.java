package exception;

import static service.ERROR_MESSAGE.EXECUTE_SERVICE_ERROR;
import service.ERROR_MESSAGE;
public class ServiceException extends MyException {

	private static final long serialVersionUID = 1L;

	public ServiceException(ERROR_MESSAGE errorMessage) {
		super(errorMessage, null);
	}
	
	public ServiceException(Exception e) {
		super(EXECUTE_SERVICE_ERROR, e, e.getMessage());
	}
	
	public ServiceException(ERROR_MESSAGE errorMessage, Exception e) {
		super(errorMessage, e);
	}
	
}
