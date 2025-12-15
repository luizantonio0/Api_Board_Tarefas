package com.tarefas.dto.request;

import java.util.UUID;

public record TaskBoardCreationDTO (
        String title,
        String description,
        UUID author
){
}
