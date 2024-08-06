package com.projeto.crud_spring.domain.user;

public record RegisterDTO(String login, String password, UserRole role, String email) {
}
