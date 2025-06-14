package cz.dipcom.simulator.service;

import cz.dipcom.simulator.dto.BookRecordDTO;
import cz.dipcom.simulator.dto.BookRecordResponseDTO;
import cz.dipcom.simulator.entity.BookRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookImportService {

    void importAllBooks();

    BookRecordResponseDTO getBookRecord(String objectId);

    BookRecordResponseDTO saveBookRecord(BookRecordDTO bookRecordDTO);

    List<BookRecordResponseDTO> getAllBookRecords(int page, int size);

    void deleteBookRecord(String objectId);
}
