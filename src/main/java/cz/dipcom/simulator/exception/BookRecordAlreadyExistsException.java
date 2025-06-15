package cz.dipcom.simulator.exception;
/**
 * BookRecordAlreadyExistsException is a custom exception class that extends {@link RuntimeException}.
 * It is thrown when an attempt is made to create or insert a book record that already exists in the system.
 * This exception provides a specific message indicating the conflict that occurred.
 */
public class BookRecordAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new BookRecordAlreadyExistsException with the specified detail message.
     *
     * @param message the detail message explaining why the exception was thrown
     */
    public BookRecordAlreadyExistsException(String message) {
        super(message);
    }
}
