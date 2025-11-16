package exception;

public class InvalidAddressException extends RuntimeException {
    public InvalidAddressException() {
        super("Invalid address");
    }
    public InvalidAddressException(int address) {
        super("Address " + address + " is invalid");
    }

}
