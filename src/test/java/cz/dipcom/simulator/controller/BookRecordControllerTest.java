package cz.dipcom.simulator.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.dipcom.simulator.dto.BookRecordDTO;
import cz.dipcom.simulator.dto.response.BookRecordResponseDTO;
import cz.dipcom.simulator.exception.BookRecordAlreadyExistsException;
import cz.dipcom.simulator.exception.BookRecordNotFoundException;
import cz.dipcom.simulator.service.BookImportService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers = BookRecordController.class)
class BookRecordControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookImportService bookImportService;

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Get Book Record")
    void getBookRecords() throws Exception {


        // Arrange
        String objectId = "123456";

        BookRecordResponseDTO bookRecordResponseDTO = new BookRecordResponseDTO();
        bookRecordResponseDTO.setObjectId(objectId);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/books/{objectId}", objectId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        when(bookImportService.getBookRecord(objectId)).thenReturn(bookRecordResponseDTO);

        // Act
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andReturn();


        assertEquals(200, mvcResult.getResponse().getStatus(),
                "Should return status code 200");

        verify(bookImportService, times(1)).getBookRecord(objectId);
    }

    @Test
    @DisplayName("Get Book Record by inValid id")
    void getBookRecords_inValidObjectId_shouldReturnBookRecordNotFoundException() throws Exception {


        // Arrange
        String objectId = "123456";

        BookRecordResponseDTO bookRecordResponseDTO = new BookRecordResponseDTO();
        bookRecordResponseDTO.setObjectId(objectId);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/books/{objectId}", objectId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        when(bookImportService.getBookRecord(objectId))
                .thenThrow( new BookRecordNotFoundException("Book record not found"));

        // Act
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andReturn();


        assertEquals(404, mvcResult.getResponse().getStatus(),
                "Should return status code 404 NOT FOUND");

        verify(bookImportService, times(1)).getBookRecord(objectId);
    }


    @Test
    @DisplayName("Get All Book Records")
    void testGetBookRecords_returnListOfBookRecords() throws Exception {

        // Arrange
        int page = 0;
        int size = 5;

        BookRecordResponseDTO bookRecordResponseDTO1 = new BookRecordResponseDTO();
        bookRecordResponseDTO1.setObjectId("111");

        BookRecordResponseDTO bookRecordResponseDTO2 = new BookRecordResponseDTO();
        bookRecordResponseDTO2.setObjectId("222");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        when(bookImportService.getAllBookRecords(page, size))
                .thenReturn(List.of(bookRecordResponseDTO1, bookRecordResponseDTO2));

        // Act
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andReturn();


        assertEquals(200, mvcResult.getResponse().getStatus(),
                "Should return status code 200 OK");

        verify(bookImportService, times(1)).getAllBookRecords(page, size);


    }

    @Test
    @DisplayName("Create Book Record wit valid Object Id")
    void createBookRecord_validDTOWithUniqueObjectId_returnSaveBookRecordDetail() throws Exception {

        // Arrange
        String objectId = "123456";

        BookRecordDTO bookRecordDTO = new BookRecordDTO();
        bookRecordDTO.setObjectId(objectId);

        BookRecordResponseDTO bookRecordResponseDTO = new BookRecordResponseDTO();
        bookRecordResponseDTO.setObjectId(objectId);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookRecordDTO));

        when(bookImportService.saveBookRecord(any(BookRecordDTO.class)))
                .thenReturn(bookRecordResponseDTO);

        // Act
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andReturn();

        // Assert
        assertEquals(201, mvcResult.getResponse().getStatus(),
                "Should return status code 201 CREATE");

        verify(bookImportService, times(1)).saveBookRecord(any(BookRecordDTO.class));
    }

    @Test
    @DisplayName("Create Book Record with inValid Object Id")
    void createBookRecord_inValidDTOWithExistedObjectId_shouldReturnBookRecordAlreadyException() throws Exception {

        // Arrange
        String objectId = "123456";

        BookRecordDTO bookRecordDTO = new BookRecordDTO();
        bookRecordDTO.setObjectId(objectId);

        BookRecordResponseDTO bookRecordResponseDTO = new BookRecordResponseDTO();
        bookRecordResponseDTO.setObjectId(objectId);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookRecordDTO));

        when(bookImportService.saveBookRecord(any(BookRecordDTO.class)))
                .thenThrow(new BookRecordAlreadyExistsException("Book record already exists"));

        // Act
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andReturn();

        // Assert
        assertEquals(409, mvcResult.getResponse().getStatus(),
                "Should return status code 409 CONFLICT");

        verify(bookImportService, times(1)).saveBookRecord(any(BookRecordDTO.class));
    }


    @Test
    @DisplayName("Delete existed Book record")
    void deleteBookRecord() throws Exception {

        // Arrange
        String objectId = "123456";

        Map<String, String> response = Map.of(
                "message", "Book record with " + objectId +" was deleted");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/books/{objectId}", objectId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        // Act
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andReturn();

        String responseBodyAsString = mvcResult.getResponse().getContentAsString();

        Map<String, String> successDeletedResponse =
                objectMapper.readValue(responseBodyAsString, new TypeReference<Map<String, String>>() {
                });

        // Assert
        assertEquals(response.get("message"),successDeletedResponse.get("message"), "Message should be same");
        assertEquals(200, mvcResult.getResponse().getStatus(),"Should return 200 OK");

        verify(bookImportService, times(1)).deleteBookRecord(objectId);
    }

    @Test
    @DisplayName("Deleted non existed Book Record")
    void deleteBookRecord_inValidObjectId_return404() throws Exception {

        // Arrange
        String objectId = "123456";


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/books/{objectId}", objectId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        doThrow(new BookRecordNotFoundException("Book record not found"))
                .when(bookImportService).deleteBookRecord(objectId);


        // Act
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andReturn();


        // Assert
        assertEquals(404, mvcResult.getResponse().getStatus(),"Should return status code 404");

        verify(bookImportService, times(1)).deleteBookRecord(objectId);
    }



}