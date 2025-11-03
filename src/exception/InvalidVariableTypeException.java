package exception;

import model.value.Type;

public class InvalidVariableTypeException extends RuntimeException {
    public InvalidVariableTypeException(String variableName, Type type) {super("Variable " + variableName + " should be of type " + type.toString());}
    public InvalidVariableTypeException() {
        super("Invalid variable type");
    }
}
