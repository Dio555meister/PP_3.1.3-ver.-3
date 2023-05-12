package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.security.UserUserDetailsImpl;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller // класс контроллер для обработки HTTP-запросов
@RequestMapping("/user") // базовый путь для всех обработчиков запросов внутри этого контроллера
public class UserController {


    private final UserServiceImpl userService; // Поле, содержащее экземпляр UserServiceImpl для работы с пользователями
    @Autowired  // внедряем зависимость
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    // Обработчик HTTP-запроса GET, соответствующий пути "/user/info"
    @GetMapping("/info")
    public String showUser(Model model, Principal principal) {
        // Получение информации о текущем пользователе
        UserUserDetailsImpl user = (UserUserDetailsImpl) ((Authentication) principal).getPrincipal();

        // Получение списка пользователей, содержащего только текущего пользователя
        List<User> users = Collections.singletonList(userService.getUser(user.getId()));

        // Добавление списка пользователей в модель
        model.addAttribute("user", users);

        // Возвращение имени представления для отображения
        return "/user/user";
    }
}
