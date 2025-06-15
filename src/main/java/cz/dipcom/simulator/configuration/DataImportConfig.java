package cz.dipcom.simulator.configuration;

import cz.dipcom.simulator.entity.repository.BookRecordRepository;
import cz.dipcom.simulator.service.BookImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * DataImportConfig is a Spring configuration class that manages the automatic import of book records
 * at application startup, based on the value of the `import` property.
 * It ensures that book records are imported when necessary, either by adding new data or clearing
 * existing records before importing new data.
 */
@Configuration
public class DataImportConfig {

    /**
     * The repository for interacting with the book records
     */
    private final BookRecordRepository bookRecordRepository;

    /**
     * The service used for importing books
     */
    private final BookImportService bookImportService;

    /**
     * This field holds the configuration value for enabling or disabling the book import
     */
    @Value("${import:false}")
    private boolean importEnabled;


    /**
     * Constructor to initialize the configuration with the provided repository and service.
     *
     * @param theBookRecordRepository the repository for interacting with the book records
     * @param theBookImportService the service used for importing books
     */
    @Autowired
    public DataImportConfig(BookRecordRepository theBookRecordRepository,
                            BookImportService theBookImportService) {
        bookRecordRepository = theBookRecordRepository;
        bookImportService = theBookImportService;
    }


    /**
     * Defines a CommandLineRunner bean that runs at application startup.
     * If the import is enabled (based on the `import` property), it checks if book records
     * already exist in the repository. If no records exist, it starts an import process.
     * If records exist, it deletes them and imports fresh data.
     *
     * @return a CommandLineRunner instance that performs the import operation at startup
     */
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
