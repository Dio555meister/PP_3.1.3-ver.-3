package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Метод findByEmail выполняет запрос на выборку пользователя по электронной почте.
    // В запросе используется объединение (left join fetch) с ролями пользователя.
    // Параметр email указывает значение для условия сравнения.

    @Query("Select u from User u left join fetch u.roles where u.email=:email")
    Optional<User> findByEmail(String email);

    // Метод findUserById выполняет поиск пользователя по идентификатору.
    // Параметр id указывает идентификатор пользователя.
    User findUserById(Integer id);

}
