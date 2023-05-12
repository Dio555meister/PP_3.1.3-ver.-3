package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Component // компонент спринг контейнера
public class InitUsers implements CommandLineRunner {

    // Поле, содержащее экземпляр UserRepository для работы с пользователями в базе данных
    private final UserRepository userRepository;

    // Поле, содержащее экземпляр RoleRepository для работы с ролями пользователей в базе данных
    private final RoleRepository roleRepository;

    // Поле, содержащее экземпляр PasswordEncoder для кодирования паролей пользователей
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InitUsers(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Метод, который будет выполнен после запуска приложения
    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Проверка, что в базе данных отсутствуют пользователи
        if (userRepository.count() == 0) {
            // Создание ролей
            Role roleAdmin = new Role("ROLE_ADMIN");
            Role roleUser = new Role("ROLE_USER");

            // Сохранение ролей в репозитории
            roleRepository.save(roleAdmin);
            roleRepository.save(roleUser);

            // Создание пользователя
            User user = new User();
            user.setName("User");
            user.setSurname("Userov");
            user.setAge(18);
            user.setEmail("user@mail.ru");
            user.setPassword(passwordEncoder.encode("user"));
            user.setRoles(List.of(roleUser));

            // Сохранение пользователя в репозитории
            userRepository.save(user);

            // Создание администратора
            User admin = new User();
            admin.setName("Admin");
            admin.setSurname("Adminov");
            admin.setAge(99);
            admin.setEmail("admin@mail.ru");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRoles(List.of(roleAdmin, roleUser));

            // Сохранение администратора в репозитории
            userRepository.save(admin);
        }
    }
}
