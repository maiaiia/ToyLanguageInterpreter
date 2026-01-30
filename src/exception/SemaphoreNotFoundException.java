package exception;

public class SemaphoreNotFoundException extends RuntimeException {
    public SemaphoreNotFoundException(String message) {
        super(message);
    }
    public SemaphoreNotFoundException(int semaphoreId) {
        super("Semaphore " + semaphoreId + " not found");
    }
}
