package kz.kaznitu.lessons.repas;

import kz.kaznitu.lessons.mod.Institut;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InstitutRepas extends CrudRepository<Institut, Long> {
    Optional<Institut> findById(Long id);
}
