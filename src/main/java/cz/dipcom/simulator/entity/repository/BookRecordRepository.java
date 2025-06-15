package cz.dipcom.simulator.entity.repository;

import cz.dipcom.simulator.entity.BookRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * BookRecordRepository is a Spring Data JPA repository interface for performing CRUD operations
 * on the {@link BookRecord} entity. It extends {@link JpaRepository} to provide built-in methods
 * for managing book records in the database.
 * This repository also includes a custom method for finding a book record by its object ID.
 */
public interface BookRecordRepository extends JpaRepository<BookRecord, Long> {


    /**
     * Finds a book record by its unique object ID.
     *
     * @param objectId the unique object ID of the book record
     * @return an {@link Optional} containing the book record if found, or an empty {@link Optional} if not
     */
    Optional<BookRecord> findByObjectId(String objectId);
}
