package kz.kaznitu.lessons.repas;

import kz.kaznitu.lessons.mod.Registration;
import kz.kaznitu.lessons.mod.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepas extends CrudRepository<Users, Long> {
    Optional<Users> findByLogin(String login);
    //Optional<Users> findById(Long id);

}
