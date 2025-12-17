package com.tarefas.dto.response;

import com.tarefas.model.enums.task.TaskStatus;
import com.tarefas.model.task.Task;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record TaskResponseDTO(
        UUID id,
        String name,
        String description,
        UserSimpleResponseDTO creator,
        UserSimpleResponseDTO manager,
        UUID completedBy,
        TaskStatus status,
        boolean completed,
        boolean cancelled,
        boolean IsBlocked,
        UUID taskColumn,
        UUID taskBoard,
        OffsetDateTime enteredCurrentColumnAt,
        List<BlockedTaskResponseDTO> blocked,
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
                        new UserSimpleResponseDTO(
                                task.getCreator()),
                        new UserSimpleResponseDTO(
                                task.getManager()),
                        task.getCompletedBy(),
                        task.getStatus(),
                        task.getCompleted(),
                        task.getCancelled(),
                        task.IsBlocked(),
                        task.getTaskColumn().getId(),
                        task.getTaskBoard().getId(),
                        task.getEnteredCurrentColumnAt(),
                        task.getBlocked().stream().map(
                                BlockedTaskResponseDTO::new
                        ).toList(),
                        task.getCreatedAt(),
                        task.getCompletionDate(),
                        task.getCompletedDate(),
                        task.getStartDate() );
        }
}
