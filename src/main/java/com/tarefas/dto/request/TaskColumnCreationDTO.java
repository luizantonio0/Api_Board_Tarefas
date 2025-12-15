package com.tarefas.dto.request;

import java.util.UUID;

public record TaskColumnCreationDTO(
        String title,
        String description,
        UUID author,
        UUID taskBoard
) {
}
