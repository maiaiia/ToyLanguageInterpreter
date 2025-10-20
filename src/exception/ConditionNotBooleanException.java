package exception;

public class ConditionNotBooleanException extends RuntimeException {
    public ConditionNotBooleanException() {
        super("Condition does not evaluate to a boolean");
    }
}
