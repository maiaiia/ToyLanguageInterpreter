package exception;

public class LatchNotFoundException extends RuntimeException {
    public LatchNotFoundException(String message) {
        super(message);
    }
    public LatchNotFoundException(int latchId) {
        super("Latch not found for id: " + latchId);
    }
}
