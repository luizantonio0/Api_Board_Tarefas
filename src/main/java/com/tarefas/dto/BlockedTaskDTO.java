package com.tarefas.dto;

import com.tarefas.model.task.changes_tasks.ChangeMadeByUser;


public record BlockedTaskDTO (
        String id,
        String reasonBlocked,
        ChangeMadeByUser User,
        boolean blocked
){

}
