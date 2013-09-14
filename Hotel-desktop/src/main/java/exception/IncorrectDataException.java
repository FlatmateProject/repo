package exception;

public class IncorrectDataException extends Exception {

    public IncorrectDataException() {
        super();
        printStackTrace();
    }

    public IncorrectDataException(DAOException e) {
        super(e);
        e.printStackTrace();
    }
}
