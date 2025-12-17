package com.tarefas.dto.response;

import com.tarefas.model.TaskColumn;

import java.util.List;
import java.util.UUID;

public record TaskColumnResponseDTO(
        UUID id,
        String title,
        String description,
        UserSimpleResponseDTO author,
        UUID taskBoard,
        List<TaskResponseDTO> tasks
) {
    public TaskColumnResponseDTO(TaskColumn taskColumn){
        this(
                taskColumn.getId(),
                taskColumn.getTitle(),
                taskColumn.getDescription(),
                new UserSimpleResponseDTO(taskColumn.getAuthor()),
                taskColumn.getTaskBoard().getId(),
                (taskColumn.getTasks() != null)?
                taskColumn.getTasks().stream().map(TaskResponseDTO :: new).toList()
                        : null
        );
    }
}
