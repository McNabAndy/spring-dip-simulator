package cz.dipcom.simulator.exception;

public class BookRecordAlreadyExistsException extends RuntimeException {
    public BookRecordAlreadyExistsException(String message) {
        super(message);
    }
}
