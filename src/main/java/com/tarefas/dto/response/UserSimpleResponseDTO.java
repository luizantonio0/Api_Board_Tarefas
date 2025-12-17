package com.tarefas.dto.response;

import java.util.UUID;

public record UserSimpleResponseDTO (
    UUID id,
    String name
){
    public UserSimpleResponseDTO(com.tarefas.model.User user){
        this(user.getId(), user.getName());
    }
}
