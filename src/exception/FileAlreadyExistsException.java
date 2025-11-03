package exception;

public class FileAlreadyExistsException extends RuntimeException {
    public FileAlreadyExistsException(String message) {
        super(message);
    }
    public FileAlreadyExistsException() {super("File Already Exists");}
}
