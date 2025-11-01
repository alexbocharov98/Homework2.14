package model.exception;

public class NoSuchProductException extends RuntimeException {
    public NoSuchProductException() {
        super();
    }

    public NoSuchProductException(String message) {
        super(message);
    }
}
