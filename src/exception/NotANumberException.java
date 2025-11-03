package exception;

import model.value.IValue;

public class NotANumberException extends RuntimeException {
    public NotANumberException() {
        super("Value is not a number");
    }
}
