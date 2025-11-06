package exception;

import model.type.IType;

public class InvalidVariableTypeException extends RuntimeException {
    public InvalidVariableTypeException(String variableName, IType type) {super("Variable " + variableName + " should be of type " + type.toString());}
    public InvalidVariableTypeException() {
        super("Invalid variable type");
    }
}
