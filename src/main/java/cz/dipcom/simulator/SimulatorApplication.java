package cz.dipcom.simulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * The main entry point for the Spring Boot application.
 * This class contains the main method that launches the Spring Boot application by calling {@link SpringApplication#run(Class, String...)}.
 * The application is annotated with {@link SpringBootApplication}, which enables auto-configuration, component scanning,
 * and configuration of the application context.
 */
@SpringBootApplication
public class SimulatorApplication {

    /**
     * The main method that serves as the entry point for the Spring Boot application.
     * It initializes and runs the application.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(SimulatorApplication.class, args);
    }
}
