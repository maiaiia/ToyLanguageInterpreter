package exception;

import model.value.Type;

public class InvalidExpressionTypeException extends RuntimeException {
    public InvalidExpressionTypeException() {
        super("Invalid Expression Type");
    }
    public InvalidExpressionTypeException(Type type) {
        super("Expression should evaluate to type " + type.toString());
    }
}
