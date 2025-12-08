package exception;

public class InvalidOperandTypeException extends RuntimeException {
    public InvalidOperandTypeException() {
        super("Invalid operand type");
    }
    public InvalidOperandTypeException(String message) {
        super(message);
    }
}
