package cz.dipcom.simulator.service;

import aj.org.objectweb.asm.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.dipcom.simulator.dto.BookRecordDTO;
import cz.dipcom.simulator.dto.BookRecordResponseDTO;
import cz.dipcom.simulator.dto.mapper.BookRecordMapper;
import cz.dipcom.simulator.entity.BookRecord;
import cz.dipcom.simulator.entity.repository.BookRecordRepository;
import cz.dipcom.simulator.exception.BookRecordAlreadyExistsException;
import cz.dipcom.simulator.exception.BookRecordNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookImportServiceImplTest {


    @Mock
    private BookRecordRepository bookRecordRepository;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private BookRecordMapper bookRecordMapper;

    @InjectMocks
    private BookImportServiceImpl bookImportService;


    private String objectId;

    private BookRecord bookRecord;

    private BookRecordDTO bookRecordDTO;

    private BookRecordResponseDTO bookRecordResponseDTO;



    @BeforeEach
    void setUp() {

        objectId = "123";

        bookRecord = new BookRecord();
        bookRecord.setObjectId(objectId);

        bookRecordDTO = new BookRecordDTO();
        bookRecordDTO.setObjectId(objectId);

        bookRecordResponseDTO = new BookRecordResponseDTO();
        bookRecordResponseDTO.setObjectId(objectId);

    }

    @Test
    @DisplayName("Get Book´s record by valid object_id ")
    void getBookRecord_validObjectId_returnBookRecordDetail() {

        // Arrange
        Optional<BookRecord> bookRecordOptional = Optional.of(bookRecord);

        when(bookRecordRepository.findByObjectId(objectId)).thenReturn(bookRecordOptional);
        when(bookRecordMapper.toResponseDTO(bookRecord)).thenReturn(bookRecordResponseDTO);

        // Act
        BookRecordResponseDTO actual = bookImportService.getBookRecord(objectId);

        // Assert
        assertEquals(bookRecordResponseDTO.getObjectId(), actual.getObjectId(), "Book record does not match");

        verify(bookRecordRepository, times(1)).findByObjectId(objectId);
    }

    @Test
    @DisplayName("Get Book´s record by inValid object_id ")
    void getBookRecord_inValidObjectId_shouldReturnBooKRecordNotFoundException() {

        // Arrange
        Optional<BookRecord> bookRecordOptional = Optional.empty();

        when(bookRecordRepository.findByObjectId(objectId)).thenReturn(bookRecordOptional);

        // Act and Assert
        assertThrows(BookRecordNotFoundException.class, () ->
                bookImportService.getBookRecord(objectId),
                "Should throw BookRecordNotFoundException");

        verify(bookRecordRepository, times(1)).findByObjectId(objectId);
        verify(bookRecordMapper, times(0)).toResponseDTO(bookRecord);
    }

    @Test
    @DisplayName("Create new Book´s record with valid data")
    void saveBookRecord_validBookRecordDTO_returnBookRecordDetail() {

        // Arrange
        when(bookRecordMapper.toBookRecord(bookRecordDTO)).thenReturn(bookRecord);
        when(bookRecordRepository.save(any(BookRecord.class))).thenReturn(bookRecord);
        when(bookRecordMapper.toResponseDTO(bookRecord)).thenReturn(bookRecordResponseDTO);

        // Act
        BookRecordResponseDTO actual = bookImportService.saveBookRecord(bookRecordDTO);

        // Assert
        assertEquals(bookRecordResponseDTO.getObjectId(), actual.getObjectId(),
                "Book record does not match");
    }

    @Test
    @DisplayName("Create new Book´s record with inValid data")
    void saveBookRecord_existedBookRecord_shouldReturnBookRecordAlreadyExistException() {

        // Arrange
        when(bookRecordMapper.toBookRecord(bookRecordDTO)).thenReturn(bookRecord);
        when(bookRecordRepository.save(bookRecord)).thenThrow(
                new BookRecordAlreadyExistsException("Book record already exists"));

        // Act and Assert
        assertThrows(BookRecordAlreadyExistsException.class, () -> bookImportService.saveBookRecord(bookRecordDTO),
                "Should throw BookRecordAlreadyExistsException");

        verify(bookRecordRepository, times(1)).save(bookRecord);
        verify(bookRecordMapper, times(0)).toResponseDTO(bookRecord);
    }

    @Test
    @DisplayName("Get All Books Records")
    void getAllBookRecords_returnListOfBookRecords() {

        // Arrange
        int page = 0;
        int size = 5;

        Pageable pageble = PageRequest.of(page, size);

        BookRecordResponseDTO bookRecordResponseDTO1 = new BookRecordResponseDTO();
        bookRecordResponseDTO1.setObjectId("111");

        BookRecordResponseDTO bookRecordResponseDTO2 = new BookRecordResponseDTO();
        bookRecordResponseDTO2.setObjectId("222");

        List<BookRecordResponseDTO> bookRecordResponseDTOList = List.of(bookRecordResponseDTO1, bookRecordResponseDTO2);


        BookRecord bookRecord1 = new BookRecord();
        bookRecord1.setObjectId("111");
        BookRecord bookRecord2 = new BookRecord();
        bookRecord2.setObjectId("222");

        List<BookRecord> bookRecordList = List.of(bookRecord1, bookRecord2);

        Page<BookRecord> pageBooksRecord = new PageImpl<>(bookRecordList, pageble, 1);


        when(bookRecordRepository.findAll(pageble)).thenReturn(pageBooksRecord);
        when(bookRecordMapper.toResponseDTO(bookRecord1)).thenReturn(bookRecordResponseDTO1);
        when(bookRecordMapper.toResponseDTO(bookRecord2)).thenReturn(bookRecordResponseDTO2);

        // Act
        List<BookRecordResponseDTO> actual = bookImportService.getAllBookRecords(page, size);

        // Assert
        assertEquals(bookRecordResponseDTOList, actual, "Book record list does not match");

    }

    @Test
    @DisplayName("Delete existed Books Record")
    void deleteBookRecord_existedBookRecord_shouldDeleteBookRecord() {

        // Arrange
        when(bookRecordRepository.findByObjectId(objectId)).thenReturn(Optional.of(bookRecord));

        // Act
        bookImportService.deleteBookRecord(objectId);

        // Assert
        verify(bookRecordRepository, times(1)).delete(bookRecord);

    }

    @Test
    @DisplayName("Delete NON existed Books Record")
    void deleteBookRecord_NonExistedBookRecord_shouldReturnBookRecordNotFoundException() {

        // Arrange
        when(bookRecordRepository.findByObjectId(objectId)).thenReturn(Optional.empty());

        // Assert act
        assertThrows(BookRecordNotFoundException.class, () -> bookImportService.deleteBookRecord(objectId),
                "Should throw BookRecordNotFoundException");

        verify(bookRecordRepository, never()).delete(bookRecord);

    }

}