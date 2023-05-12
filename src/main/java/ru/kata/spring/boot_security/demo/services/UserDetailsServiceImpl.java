package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.security.UserUserDetailsImpl;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    // Конструктор UserDetailsServiceImpl принимает репозиторий пользователей в качестве аргумента.
    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Метод loadUserByUsername загружает пользователя по его имени пользователя (в данном случае, по электронной почте).
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Ищем пользователя по электронной почте с помощью userRepository.
        Optional<User> user = userRepository.findByEmail(username);

        // Если пользователь не найден, выбрасываем исключение UsernameNotFoundException с сообщением "Пользователь не найден".
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }

        // Создаем объект UserUserDetailsImpl, используя найденного пользователя.
        return new UserUserDetailsImpl(user.get());
    }
}
