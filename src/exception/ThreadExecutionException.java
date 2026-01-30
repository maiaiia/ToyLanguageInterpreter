package exception;

public class ThreadExecutionException extends RuntimeException {
    public ThreadExecutionException() {
        super("Thread execution exception");
    }
    public ThreadExecutionException(String message) {
        super(message);
    }
}
