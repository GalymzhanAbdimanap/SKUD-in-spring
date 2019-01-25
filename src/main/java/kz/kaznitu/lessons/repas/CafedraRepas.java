package kz.kaznitu.lessons.repas;

import kz.kaznitu.lessons.mod.Cafedra;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CafedraRepas extends CrudRepository<Cafedra, Long> {
    Optional<Cafedra> findById(Long id);
}
