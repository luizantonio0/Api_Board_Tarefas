package com.tarefas.model.task;

import com.tarefas.model.task.changes_tasks.ChangeMadeByUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlockedTask {
    private String id;
    private String reasonBlocked;
    private String reasonUnblocked;
    private ChangeMadeByUser userBlocked;
    private ChangeMadeByUser userUnblocked;
    private boolean blocked;
}
