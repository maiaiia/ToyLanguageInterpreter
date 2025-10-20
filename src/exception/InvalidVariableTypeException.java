package exception;

public class InvalidVariableTypeException extends RuntimeException {
    public InvalidVariableTypeException() {
        super("Invalid variable type");
    }
}
