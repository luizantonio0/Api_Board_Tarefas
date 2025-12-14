package com.tarefas.model.task.changes_tasks;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.time.OffsetDateTime;
import java.util.UUID;

@Embeddable
public record ChangeMadeByUser(
        @Column(updatable=false)
        UUID userId,
        @Column(updatable=false)
        OffsetDateTime date
) {
}
