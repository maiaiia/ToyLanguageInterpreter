package exception;

public class LockNotFoundException extends RuntimeException {
    public LockNotFoundException(int lockId) {
        super("Lock with id " + lockId + " not found");
    }
}
