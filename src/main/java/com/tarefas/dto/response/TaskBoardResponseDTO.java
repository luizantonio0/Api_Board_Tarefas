package com.tarefas.dto.response;

import com.tarefas.model.TaskBoard;

import java.util.List;
import java.util.UUID;

public record TaskBoardResponseDTO(
        UUID id,
        String title,
        String description,
        UserSimpleResponseDTO author,
        //TODO: Resolver o retorno
        List<TaskColumnResponseDTO> taskColumns
) {
    public TaskBoardResponseDTO(TaskBoard taskBoard){
        this(
                taskBoard.getId(),
                taskBoard.getTitle(),
                taskBoard.getDescription(),
                new UserSimpleResponseDTO(
                        taskBoard.getAuthor()
                ),
                taskBoard.getTaskColumns().stream().map(TaskColumnResponseDTO::new).toList());
   }
}
