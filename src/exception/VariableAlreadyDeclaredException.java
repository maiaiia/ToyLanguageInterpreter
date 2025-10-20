package exception;

public class VariableAlreadyDeclaredException extends RuntimeException {
    public VariableAlreadyDeclaredException() {
        super("Variable already declared");
    }
}
