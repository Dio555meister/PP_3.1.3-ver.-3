package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;
// Аннотация @Repository указывает, что интерфейс является репозиторием,
// который предоставляет доступ к данным сущности Role

// Интерфейс RoleRepository наследует от интерфейса JpaRepository,
// предоставляющего базовые операции для работы с данными, включая CRUD-операции (создание, чтение, обновление, удаление)

// В качестве параметров у интерфейса JpaRepository указаны:
// - тип сущности, с которой будет работать репозиторий (Role)
// - тип первичного ключа сущности (Integer)
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
