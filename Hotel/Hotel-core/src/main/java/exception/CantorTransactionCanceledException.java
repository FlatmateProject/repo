package exception;

public class CantorTransactionCanceledException extends Exception {

    public CantorTransactionCanceledException(Exception e) {
        super(e);
    }

    public CantorTransactionCanceledException(String message) {
        super(message);
    }
}
