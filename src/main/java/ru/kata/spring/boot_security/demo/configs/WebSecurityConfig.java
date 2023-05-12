package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ru.kata.spring.boot_security.demo.services.UserDetailsServiceImpl;



//  Конфигурация безопасности веб-приложения.

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final UserDetailsServiceImpl userDetailsService;
    private final SuccessUserHandler successUserHandler;

    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl uDetailsService, SuccessUserHandler successUserHandler) {
        this.userDetailsService = uDetailsService;
        this.successUserHandler = successUserHandler;
    }

     //Переопределение метода configure для настройки безопасности HTTP.

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")  // Доступ только для пользователей с ролью "ADMIN"
                .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")  // Доступ для пользователей с ролью "ADMIN" или "USER"
                .and()
                .formLogin().successHandler(successUserHandler)  // Настройка формы входа и обработчика успешной аутентификации
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login");  // Настройка выхода из системы и страницы перенаправления после выхода
    }


     // Создание бина PasswordEncoder для шифрования паролей.

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


     // Переопределение метода configure для настройки аутентификации.

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
    }
}