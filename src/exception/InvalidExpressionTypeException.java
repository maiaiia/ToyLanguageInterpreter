package exception;

import model.type.IType;

public class InvalidExpressionTypeException extends RuntimeException {
    public InvalidExpressionTypeException() {
        super("Invalid Expression Type");
    }
    public InvalidExpressionTypeException(IType desiredType) {
        super("Expression should evaluate to type " + desiredType.toString());
    }
    public InvalidExpressionTypeException(String message) {
        super(message);
    }
}
