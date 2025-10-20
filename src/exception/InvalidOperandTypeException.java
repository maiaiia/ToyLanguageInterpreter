package exception;

// TODO customize exception message

public class InvalidOperandTypeException extends RuntimeException {
    public InvalidOperandTypeException() {
        super("Invalid operand type");
    }
}
