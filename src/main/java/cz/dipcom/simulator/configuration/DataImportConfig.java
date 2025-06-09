package cz.dipcom.simulator.configuration;

import cz.dipcom.simulator.entity.repository.BookRecordRepository;
import cz.dipcom.simulator.service.BookImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataImportConfig {

    private BookRecordRepository bookRecordRepository;

    private BookImportService bookImportService;

    @Autowired
    public DataImportConfig(BookRecordRepository theBookRecordRepository,
                            BookImportService theBookImportService) {
        bookRecordRepository = theBookRecordRepository;
        bookImportService = theBookImportService;
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            long count = bookRecordRepository.count();
            if (count == 0) {
                System.out.println("No book records found, running import...");
                bookImportService.importAllBooks();
            } else {
                System.out.println("Book records already imported (" + count + "), skipping.");
            }
        };
    }

}
