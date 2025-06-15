package cz.dipcom.simulator.entity.repository;

import cz.dipcom.simulator.entity.Segment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * SegmentRepository is a Spring Data JPA repository interface for performing CRUD operations
 * on the {@link Segment} entity. It extends {@link JpaRepository} to provide built-in methods
 * for managing segments in the database. This repository allows operations on the `Segment` entity,
 * which may represent parts of resources related to book records.
 */
public interface SegmentRepository extends JpaRepository<Segment, Long> {
}
