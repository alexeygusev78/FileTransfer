package ru.ag78.utils.ft;

public class FileTransferException extends RuntimeException {

    public FileTransferException() {
        super();
    }

    public FileTransferException(String message) {
        super(message);
    }

    public FileTransferException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileTransferException(Throwable cause) {
        super(cause);
    }

    protected FileTransferException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
