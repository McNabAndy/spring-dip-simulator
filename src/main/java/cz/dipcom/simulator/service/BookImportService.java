package cz.dipcom.simulator.service;

import cz.dipcom.simulator.dto.BookRecordDTO;
import cz.dipcom.simulator.dto.BookRecordResponseDTO;


import java.util.List;

/**
 * BookImportService is an interface that defines the methods for managing book records in the system.
 * It includes functionality for importing books, retrieving, saving, updating, and deleting book records.
 * The service is responsible for handling the business logic associated with book record management.
 */
public interface BookImportService {


    /**
     * Imports all books into the system. This method triggers the import process for all available book records.
     */
    void importAllBooks();


    /**
     * Retrieves a specific book record based on the unique object ID.
     *
     * @param objectId the unique identifier for the book record
     * @return a {@link BookRecordResponseDTO} containing the details of the requested book record
     */
    BookRecordResponseDTO getBookRecord(String objectId);


    /**
     * Saves a new book record in the system.
     *
     * @param bookRecordDTO the data transfer object (DTO) containing the information to save
     * @return a {@link BookRecordResponseDTO} containing the saved book record details
     */
    BookRecordResponseDTO saveBookRecord(BookRecordDTO bookRecordDTO);


    /**
     * Retrieves a list of all book records with pagination support.
     *
     * @param page the page number to retrieve
     * @param size the number of records per page
     * @return a list of {@link BookRecordResponseDTO} containing the details of all retrieved book records
     */
    List<BookRecordResponseDTO> getAllBookRecords(int page, int size);


    /**
     * Deletes a specific book record based on the unique object ID.
     *
     * @param objectId the unique identifier for the book record to delete
     */
    void deleteBookRecord(String objectId);
}
