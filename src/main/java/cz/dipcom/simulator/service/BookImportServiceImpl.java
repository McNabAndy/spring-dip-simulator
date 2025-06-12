package cz.dipcom.simulator.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.dipcom.simulator.dto.BookRecordResponseDTO;
import cz.dipcom.simulator.dto.mapper.BookRecordMapper;
import cz.dipcom.simulator.entity.BookRecord;
import cz.dipcom.simulator.entity.Resource;
import cz.dipcom.simulator.entity.Segment;
import cz.dipcom.simulator.entity.repository.BookRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class BookImportServiceImpl implements BookImportService {


    private final BookRecordRepository bookRecordRepository;

    private final ObjectMapper objectMapper;


    private BookRecordMapper bookRecordMapper;

    @Autowired
    public BookImportServiceImpl(BookRecordRepository theBookRecordRepository,
                                 ObjectMapper theObjectMapper, BookRecordMapper theBookRecordMapper
                                 ) {
        bookRecordRepository = theBookRecordRepository;
        objectMapper = theObjectMapper;
        bookRecordMapper = theBookRecordMapper;


    }

    @Cacheable(cacheNames = "bookRecord", key = "#id")
    @Override
    public BookRecordResponseDTO getBookRecord(Long id) {
        BookRecord bookRecord = bookRecordRepository.getReferenceById(id);
        return bookRecordMapper.toResponseDTO(bookRecord);
    }

    @Override
    public List<BookRecordResponseDTO> getAllBookRecords(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BookRecord> pageBookRecords = bookRecordRepository.findAll(pageable);

        return pageBookRecords.getContent().stream()
                .map(record ->
                    bookRecordMapper.toResponseDTO(record))
                .toList();
    }

    @Override
    public void deleteBookRecord(Long id) {
        bookRecordRepository.deleteById(id);
    }


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
