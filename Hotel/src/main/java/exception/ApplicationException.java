package exception;

public class ApplicationException extends DAOException {

    public ApplicationException(Exception exception) {
        super(exception);
    }
}
