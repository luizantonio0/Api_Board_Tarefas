package com.tarefas.changes_tasks;

import com.tarefas.model.User;

import java.time.OffsetDateTime;

public record ChangesMadeByUser(
        User author,
        OffsetDateTime date
) {
}
