package com.tarefas.dto.response;

import com.tarefas.model.task.BlockedTask;
import com.tarefas.model.task.changes_tasks.ChangeMadeByUser;
import jakarta.persistence.*;

import java.util.UUID;

public record BlockedTaskResponseDTO (
        UUID id,
        String reasonBlocked,
        String reasonUnblocked,
        ChangeMadeByUser userBlocked,
        ChangeMadeByUser userUnblocked,
        boolean blocked

){
    public BlockedTaskResponseDTO(BlockedTask blockedTask){
        this(blockedTask.getId(), blockedTask.getReasonBlocked(), blockedTask.getReasonUnblocked(), blockedTask.getUserBlocked(), blockedTask.getUserUnblocked(), blockedTask.isBlocked());
    }
}
