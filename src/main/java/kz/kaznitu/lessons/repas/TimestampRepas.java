package kz.kaznitu.lessons.repas;

import kz.kaznitu.lessons.mod.Timestamp;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TimestampRepas extends CrudRepository<Timestamp, Long> {
    Optional<Timestamp>findById(Long id);
}
