package ru.kata.spring.boot_security.demo.security;

import org.springframework.security.core.GrantedAuthority;
import ru.kata.spring.boot_security.demo.models.Role;

// Класс RoleGrantAuthorityImpl реализует интерфейс GrantedAuthority.
public class RoleGrantAuthorityImpl implements GrantedAuthority {
    private final Role role;

    // Конструктор RoleGrantAuthorityImpl принимает объект роли в качестве аргумента.
    public RoleGrantAuthorityImpl(Role role) {
        this.role = role;
    }
    // Метод getAuthority возвращает имя роли.
    @Override
    public String getAuthority() {
        return role.getName();
    }
}