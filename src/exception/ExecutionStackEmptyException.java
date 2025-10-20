package exception;

public class ExecutionStackEmptyException extends RuntimeException {
    public ExecutionStackEmptyException() {
        super("Execution stack is empty");
    }
}
