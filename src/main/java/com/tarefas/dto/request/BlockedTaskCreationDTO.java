package com.tarefas.dto.request;

import java.util.UUID;

public record BlockedTaskCreationDTO
(
    UUID userId,
    String reasonBlocked,
    boolean blocked
)
{}
