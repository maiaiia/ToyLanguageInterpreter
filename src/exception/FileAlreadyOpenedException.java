package exception;

public class FileAlreadyOpenedException extends RuntimeException {
    public FileAlreadyOpenedException(String message) {
        super(message);
    }
    public FileAlreadyOpenedException(){super("File Already Opened");}
}
