package exception;


public class DAOException extends Exception {

    private static final long serialVersionUID = 1L;

    public DAOException() {
    }

    public DAOException(Exception exception) {
        super(exception);
        exception.printStackTrace();
    }

    public DAOException(Exception e, String errorMessage) {
        super(errorMessage);
        e.printStackTrace();
    }

    public DAOException(String errorMessage) {
        super(errorMessage);
    }
}
