package exception;

public class VariableNotDefinedException extends RuntimeException {
    public VariableNotDefinedException(String message) {super(message);}
    public VariableNotDefinedException() {
        super("Variable is not defined");
    }
}
