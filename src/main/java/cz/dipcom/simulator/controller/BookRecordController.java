package cz.dipcom.simulator.controller;

import cz.dipcom.simulator.dto.BookRecordDTO;
import cz.dipcom.simulator.dto.response.BookRecordResponseDTO;
import cz.dipcom.simulator.service.BookImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * BookRecordController handles HTTP requests related to book records.
 * It provides endpoints for retrieving, creating, and deleting book records.
 * This controller interacts with the BookImportService to perform operations on book data.
 */
@RestController
@RequestMapping("/api/books")
public class BookRecordController {

    /**
     * the service for managing book records
     */
    private final BookImportService bookImportService;

    /**
     * Constructor for initializing the controller with the BookImportService.
     *
     * @param theBookImportService the service for managing book records
     */
    @Autowired
    public BookRecordController(BookImportService theBookImportService) {
        bookImportService = theBookImportService;
    }


    /**
     * Retrieves a book record by its unique object ID.
     *
     * @param objectId the unique identifier of the book record
     * @return a ResponseEntity containing the book record details
     */
    @GetMapping("/{objectId}")
    public ResponseEntity<BookRecordResponseDTO> getBookRecord(@PathVariable String objectId) {
        return new ResponseEntity<>(bookImportService.getBookRecord(objectId), HttpStatus.OK);
    }

    /**
     * Retrieves all book records with pagination support.
     *
     * @param page the page number to retrieve (default is 0)
     * @param size the number of records per page (default is 5)
     * @return a ResponseEntity containing a list of book records
     */
    @GetMapping
    public ResponseEntity<List<BookRecordResponseDTO>> getAllBookRecords(@RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "5") int size){
        return new ResponseEntity<>(bookImportService.getAllBookRecords(page, size), HttpStatus.OK);

    }

    /**
     * Creates a new book record.
     *
     * @param bookRecordDTO the data transfer object containing the new book record's information
     * @return a ResponseEntity containing the created book record
     */
    @PostMapping
    public ResponseEntity<BookRecordResponseDTO> createBookRecord(@RequestBody BookRecordDTO bookRecordDTO){
        return new ResponseEntity<>(bookImportService.saveBookRecord(bookRecordDTO), HttpStatus.CREATED);
    }


    /**
     * Deletes a book record by its unique object ID.
     *
     * @param objectId the unique identifier of the book record to delete
     * @return a ResponseEntity with a message indicating successful deletion
     */
    @DeleteMapping("/{objectId}")
    public ResponseEntity<Map<String, String>> deleteBookRecord(@PathVariable String objectId){
        bookImportService.deleteBookRecord(objectId);
        return new ResponseEntity<>(Map.of("message", "Book record with " + objectId +" was deleted"), HttpStatus.OK);
    }
}
