package kz.kaznitu.lessons.repas;

import kz.kaznitu.lessons.mod.Registration;
import kz.kaznitu.lessons.spm.listener;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RegistrationRepas extends CrudRepository<Registration, Long> {
    Optional<Registration> findById(Long id);



}
