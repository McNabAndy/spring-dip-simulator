package cz.dipcom.simulator.controller;

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


    @GetMapping("/{id}")
    public ResponseEntity<BookRecordResponseDTO> getBookRecords(@PathVariable Long id){
        return new ResponseEntity<>(bookImportService.getBookRecord(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BookRecordResponseDTO>> getBookRecords(@RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "5") int size){
        return new ResponseEntity<>(bookImportService.getAllBookRecords(page, size), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteBookRecord(@PathVariable Long id){
        bookImportService.deleteBookRecord(id);
        return new ResponseEntity<>(Map.of("message", "Book record with " + id +" was deleted"), HttpStatus.OK);
    }
}
