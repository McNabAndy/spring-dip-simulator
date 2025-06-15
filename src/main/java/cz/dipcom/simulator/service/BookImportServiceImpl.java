package cz.dipcom.simulator.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.dipcom.simulator.dto.BookRecordDTO;
import cz.dipcom.simulator.dto.response.BookRecordResponseDTO;
import cz.dipcom.simulator.dto.mapper.BookRecordMapper;
import cz.dipcom.simulator.entity.BookRecord;
import cz.dipcom.simulator.entity.Resource;
import cz.dipcom.simulator.entity.Segment;
import cz.dipcom.simulator.entity.repository.BookRecordRepository;
import cz.dipcom.simulator.exception.BookRecordAlreadyExistsException;
import cz.dipcom.simulator.exception.BookRecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;

/**
 * BookImportServiceImpl is a service implementation that handles business logic related to book records.
 * It provides functionality to import, retrieve, save, and delete book records. The service interacts with
 * the BookRecordRepository and uses a custom mapper for converting between DTOs and entities.
 */
@Service
public class BookImportServiceImpl implements BookImportService {

    /**
     * The repository for performing CRUD operations on book records.
     */
    private final BookRecordRepository bookRecordRepository;


    /**
     * The ObjectMapper used for converting JSON data to Java objects.
     */
    private final ObjectMapper objectMapper;


    /**
     *  The mapper used for converting between BookRecordDTO and BookRecord entities.
     */
    private final BookRecordMapper bookRecordMapper;


    /**
     * Constructor for initializing the BookImportServiceImpl with the required dependencies.
     *
     * @param theBookRecordRepository the repository for performing CRUD operations on book records
     * @param theObjectMapper the ObjectMapper used for converting JSON data to Java objects
     * @param theBookRecordMapper the mapper used for converting between BookRecordDTO and BookRecord entities
     */
    @Autowired
    public BookImportServiceImpl(BookRecordRepository theBookRecordRepository,
                                 ObjectMapper theObjectMapper, BookRecordMapper theBookRecordMapper
                                 ) {
        bookRecordRepository = theBookRecordRepository;
        objectMapper = theObjectMapper;
        bookRecordMapper = theBookRecordMapper;
    }



    /**
     * Retrieves a specific book record by its object ID.
     *
     * @param objectId the unique identifier for the book record
     * @return a {@link BookRecordResponseDTO} containing the details of the requested book record
     * @throws BookRecordNotFoundException if the book record with the given object ID does not exist
     */
    @Cacheable(cacheNames = "bookRecord", key = "#objectId")
    @Override
    public BookRecordResponseDTO getBookRecord(String objectId) {

        BookRecord bookRecord = bookRecordRepository.findByObjectId(objectId)
                .orElseThrow(()-> new BookRecordNotFoundException("Book record with Object_id " + objectId + " not found"));

        return bookRecordMapper.toResponseDTO(bookRecord);
    }



    /**
     * Saves a new book record to the system.
     *
     * @param bookRecordDTO the data transfer object (DTO) containing the book record's information
     * @return a {@link BookRecordResponseDTO} containing the saved book record details
     * @throws BookRecordAlreadyExistsException if a book record with the same object ID already exists
     */
    @Override
    public BookRecordResponseDTO saveBookRecord(BookRecordDTO bookRecordDTO) {

        BookRecord bookRecord = bookRecordMapper.toBookRecord(bookRecordDTO);
        try{
            BookRecord savedBookRecord = bookRecordRepository.save(bookRecord);
            return bookRecordMapper.toResponseDTO(savedBookRecord);
        } catch (DataIntegrityViolationException e){
          throw new BookRecordAlreadyExistsException(
                  "Book record with Object_id: " + bookRecordDTO.getObjectId() + "  already exists");
        }
    }



    /**
     * Retrieves all book records, with pagination support.
     *
     * @param page the page number to retrieve
     * @param size the number of records per page
     * @return a list of {@link BookRecordResponseDTO} containing details of the retrieved book records
     */
    @Override
    public List<BookRecordResponseDTO> getAllBookRecords(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BookRecord> pageBookRecords = bookRecordRepository.findAll(pageable);

        return pageBookRecords.getContent().stream()
                .map(record ->
                    bookRecordMapper.toResponseDTO(record))
                .toList();
    }



    /**
     * Deletes a specific book record by its object ID.
     *
     * @param objectId the unique identifier of the book record to delete
     * @throws BookRecordNotFoundException if the book record with the given object ID does not exist
     */
    @CacheEvict(cacheNames = "bookRecord", key = "#objectId")
    @Override
    public void deleteBookRecord(String objectId) {
        BookRecord bookRecord = bookRecordRepository.findByObjectId(objectId)
                .orElseThrow(() -> new BookRecordNotFoundException("Book record with id " + objectId + " not found"));

            bookRecordRepository.delete(bookRecord);
    }



    /**
     * Imports all book records from a predefined JSON file.
     * This method reads the JSON data, converts it into book records, and saves them to the database.
     * It also processes any related resources and segments associated with the books.
     */
    @Transactional
    @Override
    public void importAllBooks() {
        try(InputStream inputStream = getClass().getResourceAsStream("/metadata.json")){
            TypeReference <List<BookRecord>> typeReference = new TypeReference<List<BookRecord>>() {};

            List<BookRecord> bookRecords = objectMapper.readValue(inputStream, typeReference);

            addResourceAndSegments(bookRecords);

            bookRecordRepository.saveAll(bookRecords);
            System.out.println("Imported: " + bookRecords.size() + " records");

        } catch (Exception e){
            throw new RuntimeException(e);

        }

    }


    // A helper method that associates resources and segments with their corresponding book records.

    /**
     * Associates resources and segments with the corresponding book records.
     *
     * @param bookRecords the list of book records to process
     */
    private void addResourceAndSegments(List<BookRecord> bookRecords) {
        for (BookRecord theBookRecord : bookRecords) {
            if (theBookRecord.getResources() != null) {
                for (Resource resource : theBookRecord.getResources()) {
                    resource.setBookRecord(theBookRecord);
                }
            }
            if (theBookRecord.getSegments() != null) {
                for (Segment segment : theBookRecord.getSegments()) {
                    segment.setBookRecord(theBookRecord);
                }
            }
        }

    }
}
