package ru.otus.homework.dbpractice.security;

public enum UserRole {

    ROLE_USER ("роль для пользователя"),
    ROLE_ADMIN ("роль без ограничений");

    public final String description;

    UserRole(String description) {
        this.description = description;
    }
}
