package cz.dipcom.simulator.entity.repository;

import cz.dipcom.simulator.entity.BookRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ItemRepository is a Spring Data JPA repository interface for performing CRUD operations
 * on the {@link BookRecord} entity. It extends {@link JpaRepository} to provide built-in methods
 * for managing book records in the database. This repository is focused on handling operations
 * related to the `BookRecord` entity, which includes various attributes and items related to books.
 */
public interface ItemRepository extends JpaRepository<BookRecord, Long> {
}
