package com.tarefas.dto.response;

import com.tarefas.model.TaskBoard;
import com.tarefas.model.TaskColumn;

import java.util.List;
import java.util.UUID;

public record TaskBoardResponseDTO(
        UUID id,
        String title,
        String description,
        UUID author,
        //TODO: Resolver o retorno
        List<TaskColumn> taskColumns,
        List<TaskResponseDTO> tasks
) {
    public TaskBoardResponseDTO(TaskBoard taskBoard){
        this(taskBoard.getId(), taskBoard.getTitle(), taskBoard.getDescription(), taskBoard.getAuthor().getId(), null, null);
    }
}
