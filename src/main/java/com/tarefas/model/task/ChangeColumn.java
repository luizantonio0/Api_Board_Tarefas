package com.tarefas.model.task;

import com.tarefas.model.TaskColumn;

import java.time.OffsetDateTime;
//Será uma coluna no DB
public record ChangeColumn(
        String id,
        TaskColumn taskColumn,
        OffsetDateTime enteredAt,
        OffsetDateTime exitAt
) {
}
