package cz.dipcom.simulator.advice;

import cz.dipcom.simulator.dto.error.ApiErrorDTO;
import cz.dipcom.simulator.exception.BookRecordAlreadyExistsException;
import cz.dipcom.simulator.exception.BookRecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * GlobalExceptionHandler is a controller advice that handles specific exceptions
 * thrown by the application. It catches exceptions related to book record processing
 * and returns appropriate HTTP responses with error details.
 */

@ControllerAdvice
public class GlobalExceptionHandler {



    /**
     * Handles the exception when a book record is not found.
     *
     * @param e the exception that occurred (BookRecordNotFoundException)
     * @return a ResponseEntity containing an ApiErrorDTO with error details
     */
    @ExceptionHandler(BookRecordNotFoundException.class)
    public ResponseEntity<ApiErrorDTO> handleBookRecordNotFoundException(RuntimeException e){

        ApiErrorDTO apiErrorDTO = new ApiErrorDTO();
        apiErrorDTO.setStatusCode(HttpStatus.NOT_FOUND.value());
        apiErrorDTO.setMessage(e.getMessage());
        apiErrorDTO.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(apiErrorDTO, HttpStatus.NOT_FOUND);

    }


    /**
     * Handles the exception when a book record already exists.
     *
     * @param e the exception that occurred (BookRecordAlreadyExistsException)
     * @return a ResponseEntity containing an ApiErrorDTO with error details
     */
    @ExceptionHandler(BookRecordAlreadyExistsException.class)
    public ResponseEntity<ApiErrorDTO> handleBookRecordAlreadyExistException(RuntimeException e){

        ApiErrorDTO apiErrorDTO = new ApiErrorDTO();
        apiErrorDTO.setStatusCode(HttpStatus.CONFLICT.value());
        apiErrorDTO.setMessage(e.getMessage());
        apiErrorDTO.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(apiErrorDTO, HttpStatus.CONFLICT);

    }






}
