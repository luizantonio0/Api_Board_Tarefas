package com.tarefas.dto.response;

import com.tarefas.model.enums.task.TaskStatus;
import com.tarefas.model.task.Task;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;
import java.util.stream.Stream;

public record TaskResponseDTO(
        UUID id,
        String name,
        String description,
        UserResponseDTO creator,
        UserResponseDTO manager,
        UUID completedBy,
        TaskStatus status,
        boolean completed,
        boolean cancelled,
        boolean IsBlocked,
        TaskColumnResponseDTO taskColumn,
        TaskBoardResponseDTO taskBoard,
        OffsetDateTime enteredCurrentColumnAt,
        Stream<BlockedTaskResponseDTO> blocked,
        OffsetDateTime createdAt,
        OffsetDateTime completionDate,
        OffsetDateTime completedDate,
        OffsetDateTime startDate
) {
        public TaskResponseDTO(Task task){
                this(
                        task.getId(),
                        task.getName(),
                        task.getDescription(),
                        new UserResponseDTO(
                                task.getCreator()),
                        new UserResponseDTO(
                                task.getManager()),
                        task.getCompletedBy(),
                        task.getStatus(),
                        task.getCompleted(),
                        task.getCancelled(),
                        task.IsBlocked(),
                        new TaskColumnResponseDTO( task.getTaskColumn()),
                        new TaskBoardResponseDTO(task.getTaskBoard()),
                        task.getEnteredCurrentColumnAt(),
                        task.getBlocked().stream().map(
                                BlockedTaskResponseDTO::new
                        ),
                        task.getCreatedAt(),
                        task.getCompletionDate(),
                        task.getCompletedDate(),
                        task.getStartDate() );
        }
}
