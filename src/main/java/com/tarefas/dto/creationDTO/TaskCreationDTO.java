package com.tarefas.dto.creationDTO;

import com.tarefas.model.TaskBoard;
import com.tarefas.model.TaskColumn;
import com.tarefas.model.User;

public record TaskCreationDTO (
        String name,
        String description,
        User creator,
        User manager,
        TaskColumn taskColumn,
        TaskBoard taskBoard
){
}
