package cz.dipcom.simulator.service;

import cz.dipcom.simulator.dto.BookRecordResponseDTO;

public interface BookImportService {

    void importAllBooks();

    BookRecordResponseDTO getBookRecord(Long id);
}
