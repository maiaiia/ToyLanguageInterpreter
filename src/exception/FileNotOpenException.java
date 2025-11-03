package exception;

public class FileNotOpenException extends RuntimeException {
    public FileNotOpenException() {
        super("File not opened");
    }
    public FileNotOpenException(String fileName) {
        super("File not opened: " + fileName);
    }
}
