package com.tarefas.dto.request;

import java.util.UUID;

public record ChangeColumnDTO(
        UUID taskId,
        UUID taskColumnId
) {
    public ChangeColumnDTO(String taskId, String taskColumnId) {
        this(UUID.fromString(taskId), UUID.fromString(taskColumnId));
    }
}
