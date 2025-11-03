package exception;

public class VariableNotDefinedException extends RuntimeException {
    public VariableNotDefinedException(String message) {super("Variable " + message + " is not defined");}
    public VariableNotDefinedException() {
        super("Variable is not defined");
    }
}
