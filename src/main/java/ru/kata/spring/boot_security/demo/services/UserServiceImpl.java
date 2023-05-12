package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    // Конструктор UserServiceImpl принимает репозиторий пользователей, репозиторий ролей и кодировщик паролей в качестве аргументов
    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Метод findAllRoles возвращает список всех ролей
    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    // Метод findAllUsers возвращает список всех пользователей
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    // Метод getUser получает пользователя по его идентификатору
    @Override
    public User getUser(int id) {
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.orElse(null);
    }

    // Метод saveUser сохраняет пользователя
    @Transactional
    @Override
    public void saveUser(User user) {
        // Кодируем пароль пользователя перед сохранением
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    // Метод updateUser обновляет пользователя
    @Transactional
    @Override
    public void updateUser(User updatedUser) {
        // Кодируем пароль обновленного пользователя перед сохранением
        updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        userRepository.save(updatedUser);
    }

    // Метод deleteUser удаляет пользователя по его идентификатору
    @Transactional
    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    // Метод existsUserByEmail проверяет существование пользователя по электронной почте
    @Override
    public boolean existsUserByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    // Метод findUserById находит пользователя по его идентификатору
    @Override
    public User findUserById(Integer id) {
        return userRepository.findUserById(id);
    }
}