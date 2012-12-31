package exception;


public class DAOException extends Exception {

    private static final long serialVersionUID = 1L;

    public DAOException() {
    }

    public DAOException(Exception exception) {
        super(exception.getMessage());
        exception.printStackTrace();
    }

}
