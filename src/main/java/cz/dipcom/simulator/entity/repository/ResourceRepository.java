package cz.dipcom.simulator.entity.repository;

import cz.dipcom.simulator.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
}
