package ru.kata.spring.boot_security.demo.configs;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;


@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {
    // Обработчик успешной аутентификации пользователя
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
// Получаем роли пользователя из объекта Authentication
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        // Проверяем, содержит ли пользователь роль "ROLE_ADMIN"
        if (roles.contains("ROLE_ADMIN")) {
            // Если пользователь является администратором, перенаправляем его на страницу /admin/users
            httpServletResponse.sendRedirect("/admin/users");
        }
        // Проверяем, содержит ли пользователь роль "ROLE_USER"
        else if (roles.contains("ROLE_USER")) {
            // Если пользователь является обычным пользователем, перенаправляем его на страницу /user/info
            httpServletResponse.sendRedirect("/user/info");
        }
        // Если пользователь не имеет ни роли "ROLE_ADMIN", ни роли "ROLE_USER"
        else {
            // Перенаправляем его на страницу /login
            httpServletResponse.sendRedirect("/login");
        }
    }
}