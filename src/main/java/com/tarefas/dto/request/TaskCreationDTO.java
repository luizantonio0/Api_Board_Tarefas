package com.tarefas.dto.request;

import java.util.UUID;

public record TaskCreationDTO (
        String name,
        String description,
        UUID creator,
        UUID manager,
        UUID taskBoard,
        UUID taskColumn

){
}
