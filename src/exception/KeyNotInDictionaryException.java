package exception;

public class KeyNotInDictionaryException extends RuntimeException {
    public KeyNotInDictionaryException(String message) {
        super(message);
    }
    public KeyNotInDictionaryException() {super("Key not in dictionary");}
}
