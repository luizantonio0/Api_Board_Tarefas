package com.tarefas.dto.request;

import java.util.UUID;

public record ChangeColumnDTO(
        UUID taskId,
        UUID taskColumnId
) {
}
