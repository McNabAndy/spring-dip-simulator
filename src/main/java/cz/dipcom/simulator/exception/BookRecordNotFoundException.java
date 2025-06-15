package cz.dipcom.simulator.exception;

/**
 * BookRecordNotFoundException is a custom exception class that extends {@link RuntimeException}.
 * It is thrown when a book record is not found in the system, typically when trying to retrieve
 * a record that doesn't exist in the database.
 * This exception provides a specific message indicating the error.
 */
public class BookRecordNotFoundException extends RuntimeException {

    /**
     * Constructs a new BookRecordNotFoundException with the specified detail message.
     *
     * @param message the detail message explaining why the exception was thrown
     */
    public BookRecordNotFoundException(String message) {
        super(message);
    }
}
