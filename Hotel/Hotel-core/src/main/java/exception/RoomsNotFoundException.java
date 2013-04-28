package exception;

public class RoomsNotFoundException extends Exception {

    public RoomsNotFoundException(Exception e) {
        super(e);
        e.printStackTrace();
    }
}
