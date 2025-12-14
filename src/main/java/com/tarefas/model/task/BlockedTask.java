package com.tarefas.model.task;

import com.tarefas.model.task.changes_tasks.ChangeMadeByUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb005_blocked_task")
public class BlockedTask {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(length = 140)
    private String reasonBlocked;
    @Column(length = 140)
    private String reasonUnblocked;
    @ManyToOne
    @JoinColumn(name = "taskId")
    private Task task;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "userId", column = @Column(name = "blockedByUserId")),
            @AttributeOverride(name = "date", column = @Column(name = "blockedAt"))
    })
    private ChangeMadeByUser userBlocked;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "userId", column = @Column(name = "unblockedByUserId")),
            @AttributeOverride(name = "date", column = @Column(name = "unblockedAt"))
    })
    private ChangeMadeByUser userUnblocked;
    private boolean blocked;


    public BlockedTask(String reasonBlocked, Task task, ChangeMadeByUser userBlocked) {
        this.reasonBlocked = reasonBlocked;
        this.reasonUnblocked = null;
        this.task = task;
        this.userBlocked = userBlocked;
        this.userUnblocked = null;
        this.blocked = true;
    }

}
