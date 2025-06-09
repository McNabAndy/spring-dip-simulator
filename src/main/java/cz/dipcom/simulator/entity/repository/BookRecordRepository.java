package cz.dipcom.simulator.entity.repository;

import cz.dipcom.simulator.entity.BookRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRecordRepository extends JpaRepository<BookRecord, Long> {
}
