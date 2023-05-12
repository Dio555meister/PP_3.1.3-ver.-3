package ru.kata.spring.boot_security.demo.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity // сущность, которая будет отображена в базе данных
@Table(name = "roles") // имя таблицы, в которой будет храниться сущность
@Setter // @Setter генерирует методы установки значений полей
@Getter // @Getter генерирует методы получения значений полей
@NoArgsConstructor //@NoArgsConstructor генерирует конструктор без аргументов
public class Role {

    @Id // @Id указывает, что поле является первичным ключом


    @GeneratedValue(strategy = GenerationType.IDENTITY) // Аннотация @GeneratedValue указывает способ генерации значения для первичного ключа
    @Column(name = "id") // Аннотация @Column указывает имя столбца в базе данных, которому соответствует поле
    private int id;
    @Column(name = "name") // @Column указывает имя столбца в базе данных, которому соответствует поле
    private String name;
    @ManyToMany // @ManyToMany указывает на связь "многие ко многим" с сущностью User

//  @JoinTable указывает таблицу, которая связывает сущности Role и User
// и определяет столбцы, которые связывают ключи сущностей в этой таблице
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Collection<User> users;

    // Конструктор класса, принимающий имя роли
    public Role(String name) {
        this.name = name;
    }

    // Переопределение иквалса
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id && Objects.equals(name, role.name);
    }

    // Переопределение метода hashCode() для вычисления хэш-кода объекта
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    // Переопределение метода toString() для получения строкового представления объекта
    @Override
    public String toString() {
        return name.toString();
    }
}