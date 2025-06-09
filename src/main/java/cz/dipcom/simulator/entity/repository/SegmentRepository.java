package cz.dipcom.simulator.entity.repository;

import cz.dipcom.simulator.entity.Segment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SegmentRepository extends JpaRepository<Segment, Long> {
}
