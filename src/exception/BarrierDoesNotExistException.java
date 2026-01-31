package exception;

public class BarrierDoesNotExistException extends RuntimeException {
    public BarrierDoesNotExistException(String message) {
        super(message);
    }
    public BarrierDoesNotExistException(Integer barrierId) {
        super("Barrier " + barrierId + " does not exist");
    }
}
