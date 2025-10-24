package exception;

public class OutOfBoundsIndexException extends RuntimeException {
    public OutOfBoundsIndexException(String message) {
        super(message);
    }
    public OutOfBoundsIndexException() {
        super("Index out of bounds");
    }
}
