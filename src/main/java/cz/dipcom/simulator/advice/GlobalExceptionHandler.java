package cz.dipcom.simulator.advice;

import cz.dipcom.simulator.dto.error.ApiErrorDTO;
import cz.dipcom.simulator.exception.BookRecordAlreadyExistsException;
import cz.dipcom.simulator.exception.BookRecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookRecordNotFoundException.class)
    public ResponseEntity<ApiErrorDTO> handleBookRecordNotFoundException(RuntimeException e){

        ApiErrorDTO apiErrorDTO = new ApiErrorDTO();
        apiErrorDTO.setStatusCode(HttpStatus.NOT_FOUND.value());
        apiErrorDTO.setMessage(e.getMessage());
        apiErrorDTO.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(apiErrorDTO, HttpStatus.NOT_FOUND);

    }


    @ExceptionHandler(BookRecordAlreadyExistsException.class)
    public ResponseEntity<ApiErrorDTO> handleBookRecordAlreadyExistException(RuntimeException e){

        ApiErrorDTO apiErrorDTO = new ApiErrorDTO();
        apiErrorDTO.setStatusCode(HttpStatus.CONFLICT.value());
        apiErrorDTO.setMessage(e.getMessage());
        apiErrorDTO.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(apiErrorDTO, HttpStatus.CONFLICT);

    }






}
