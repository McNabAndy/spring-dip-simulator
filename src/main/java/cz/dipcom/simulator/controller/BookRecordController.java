package cz.dipcom.simulator.controller;

import cz.dipcom.simulator.dto.BookRecordDTO;
import cz.dipcom.simulator.dto.BookRecordResponseDTO;
import cz.dipcom.simulator.service.BookImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookRecordController {

    private final BookImportService bookImportService;

    @Autowired
    public BookRecordController(BookImportService theBookImportService) {
        bookImportService = theBookImportService;
    }


    @GetMapping("/{objectId}")
    public ResponseEntity<BookRecordResponseDTO> getBookRecords(@PathVariable String objectId) {
        return new ResponseEntity<>(bookImportService.getBookRecord(objectId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BookRecordResponseDTO>> getBookRecords(@RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "5") int size){
        return new ResponseEntity<>(bookImportService.getAllBookRecords(page, size), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<BookRecordResponseDTO> createBookRecord(@RequestBody BookRecordDTO bookRecordDTO){
        return new ResponseEntity<>(bookImportService.saveBookRecord(bookRecordDTO), HttpStatus.CREATED);
    }


    @DeleteMapping("/{objectId}")
    public ResponseEntity<Map<String, String>> deleteBookRecord(@PathVariable String objectId){
        bookImportService.deleteBookRecord(objectId);
        return new ResponseEntity<>(Map.of("message", "Book record with " + objectId +" was deleted"), HttpStatus.OK);
    }
}
