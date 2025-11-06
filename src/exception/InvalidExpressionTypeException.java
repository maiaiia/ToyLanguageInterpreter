package exception;

import model.type.IType;

public class InvalidExpressionTypeException extends RuntimeException {
    public InvalidExpressionTypeException() {
        super("Invalid Expression Type");
    }
    public InvalidExpressionTypeException(IType type) {
        super("Expression should evaluate to type " + type.toString());
    }
}
