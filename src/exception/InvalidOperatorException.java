package exception;

public class InvalidOperatorException extends RuntimeException {
    public InvalidOperatorException() {
        super("Invalid operator");
    }
}
