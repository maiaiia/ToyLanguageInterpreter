package exception;

public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException() {
        super("File not found");
    }
    public FileNotFoundException(String fileName) {
        super("File not found: " + fileName);
    }
}
