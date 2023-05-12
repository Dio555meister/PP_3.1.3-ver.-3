package ru.kata.spring.boot_security.demo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.models.User;
import java.util.Collection;
import java.util.stream.Collectors;

// Класс UserUserDetailsImpl реализует интерфейс UserDetails

public class UserUserDetailsImpl implements UserDetails {

    private final User user;

    // Конструктор UserUserDetailsImpl принимает объект пользователя в качестве аргумента
    public UserUserDetailsImpl(User user) {
        this.user = user;
    }
    // Метод getAuthorities возвращает коллекцию ролей пользователя
    // Используется стрим для преобразования каждой роли в объект GrantedAuthority с помощью SimpleGrantedAuthority
    // Результат собирается в список.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    // Метод getPassword возвращает пароль пользователя.
    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    // Метод getUsername возвращает электронную почту пользователя в качестве имени пользователя
    @Override
    public String getUsername() {
        return this.user.getEmail();
    }

    // Метод isAccountNonExpired возвращает значение, указывающее, не истек ли срок действия учетной записи пользователя
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Метод isAccountNonLocked возвращает значение, указывающее, не заблокирована ли учетная запись пользователя
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Метод isCredentialsNonExpired возвращает значение, указывающее, не истек ли срок действия учетных данных пользователя
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Метод isEnabled возвращает значение, указывающее, включена ли учетная запись пользователя.
    @Override
    public boolean isEnabled() {
        return true;
    }

    // Метод getUser возвращает объект пользователя.
    public User getUser() {
        return this.user;
    }

    // Метод getId возвращает идентификатор пользователя.
    public int getId() {
        return this.user.getId();
    }

    // Метод toString возвращает строковое представление пользователя.
    @Override
    public String toString() {
        return user.toString();
    }
}