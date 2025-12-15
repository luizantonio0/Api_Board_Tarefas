package com.tarefas.dto.creationDTO;

import com.tarefas.model.TaskBoard;
import com.tarefas.model.TaskColumn;
import com.tarefas.model.User;

import java.util.UUID;

public record TaskCreationDTO (
        String name,
        String description,
        UUID creator,
        UUID manager,
        UUID taskColumn,
        UUID taskBoard
){
}
