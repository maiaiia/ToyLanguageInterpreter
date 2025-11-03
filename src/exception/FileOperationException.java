package exception;

import java.io.IOException;

public class FileOperationException extends RuntimeException {
    public FileOperationException(String message) {
        super(message);
    }
    public FileOperationException(IOException cause) {
        super(cause);
    }
}
