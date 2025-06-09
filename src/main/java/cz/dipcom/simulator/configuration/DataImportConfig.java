package cz.dipcom.simulator.configuration;

import cz.dipcom.simulator.entity.repository.BookRecordRepository;
import cz.dipcom.simulator.service.BookImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataImportConfig {

    private BookRecordRepository bookRecordRepository;

    private BookImportService bookImportService;

    @Value("${import:false}")
    private boolean importEnabled;

    @Autowired
    public DataImportConfig(BookRecordRepository theBookRecordRepository,
                            BookImportService theBookImportService) {
        bookRecordRepository = theBookRecordRepository;
        bookImportService = theBookImportService;
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            if (importEnabled) {
                long count = bookRecordRepository.count();
                if (count == 0) {
                    System.out.println("No book records found, running import...");
                    bookImportService.importAllBooks();
                } else {
                    System.out.println("Book records found, deleting all old data, please wait...");
                    bookRecordRepository.deleteAll();
                    System.out.println("Book records is empty, running new import...");
                    bookImportService.importAllBooks();
                }
            }

        };
    }

}
