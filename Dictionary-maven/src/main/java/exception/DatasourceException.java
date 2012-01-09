package exception;

public class DatasourceException extends ServiceException {

	private static final long serialVersionUID = 1L;
	
	public DatasourceException(String message) {
		super(message);
	}


	public DatasourceException(Exception e) {
		super(e);
	}

	
	public DatasourceException(String message, Exception e) {
		super(message, e);
	}

}
