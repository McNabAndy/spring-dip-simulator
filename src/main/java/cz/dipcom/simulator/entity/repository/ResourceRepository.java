package cz.dipcom.simulator.entity.repository;

import cz.dipcom.simulator.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ResourceRepository is a Spring Data JPA repository interface for performing CRUD operations
 * on the {@link Resource} entity. It extends {@link JpaRepository} to provide built-in methods
 * for managing resources in the database. This repository allows operations on the `Resource` entity,
 * which is related to book records and their associated resources.
 */
public interface ResourceRepository extends JpaRepository<Resource, Long> {
}
