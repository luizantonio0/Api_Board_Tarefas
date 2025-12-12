package com.tarefas.model.task.changes_tasks;

import com.tarefas.model.User;

import java.time.OffsetDateTime;

public record ChangeMadeByUser(
        User author,
        OffsetDateTime date
) {
}
