package exception;

public class UnknownOperatorException extends RuntimeException {
    public UnknownOperatorException() {
        super("Unknown operator");
    }
}
