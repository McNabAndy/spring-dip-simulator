package cz.dipcom.simulator.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.dipcom.simulator.entity.BookRecord;
import cz.dipcom.simulator.entity.repository.BookRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;

@Service
public class BookImportServiceImpl implements BookImportService {


    private BookRecordRepository bookRecordRepository;

    private ObjectMapper objectMapper;

    @Autowired
    public BookImportServiceImpl(BookRecordRepository theBookRecordRepository, ObjectMapper theObjectMapper) {
        bookRecordRepository = theBookRecordRepository;
        objectMapper = theObjectMapper;

    }


    @Transactional
    @Override
    public void importAllBooks() {
        try(InputStream inputStream = getClass().getResourceAsStream("/metadata.json")){
            TypeReference <List<BookRecord>> typeReference = new TypeReference<List<BookRecord>>() {};

            List<BookRecord> bookRecords = objectMapper.readValue(inputStream, typeReference);

            bookRecordRepository.saveAll(bookRecords);
            System.out.println("Imported: " + bookRecords.size() + " records");

        } catch (Exception e){
            throw new RuntimeException(e);

        }

    }
}
