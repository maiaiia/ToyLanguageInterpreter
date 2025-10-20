package exception;

public class VariableNotDefinedException extends RuntimeException {
    public VariableNotDefinedException() {
        super("Variable is not defined");
    }
}
