package exception;

public class SemaphoreFullException extends RuntimeException {
    public SemaphoreFullException(String message) {
        super(message);
    }
}
