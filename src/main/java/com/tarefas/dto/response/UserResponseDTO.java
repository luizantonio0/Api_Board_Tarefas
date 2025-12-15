package com.tarefas.dto.response;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String name,
        String email,
        String login,
        String role
){
}
