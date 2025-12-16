package com.tarefas.dto.response;

import com.tarefas.model.User;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String name,
        String email,
        String login,
        String role
){
    public UserResponseDTO(User user){
        this(user.getId(), user.getName(), user.getEmail(), user.getLogin(), user.getRole().toString());
    }
}
