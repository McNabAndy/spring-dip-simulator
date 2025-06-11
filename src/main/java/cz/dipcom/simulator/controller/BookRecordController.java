package cz.dipcom.simulator.controller;

import cz.dipcom.simulator.dto.BookRecordResponseDTO;
import cz.dipcom.simulator.service.BookImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
