package com.tarefas.model.task;


import com.tarefas.dto.request.TaskCreationDTO;
import com.tarefas.model.TaskBoard;
import com.tarefas.model.TaskColumn;
import com.tarefas.model.User;
import com.tarefas.model.enums.task.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb002_task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(length = 30, nullable = false)
    private String name;
    // Só o manager pode alterar dados da task
    private String description;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "userCreatorId",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_tb001_tb002_c")
    )
    private User creator;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "userManagerId",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_tb001_tb002_m")
    )
    private User manager;
    private UUID completedBy;
    private TaskStatus status;
    private boolean completed;
    private boolean cancelled;
    private boolean IsBlocked;
    //TODO: deve registrar o tempo que demorou em cada coluna(insert AT, moved AT)

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "taskColumnId",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_tb003_tb002")
    )
    private TaskColumn taskColumn;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "taskBoardId",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_tb004_tb002")
    )
    private TaskBoard taskBoard;

    private OffsetDateTime enteredCurrentColumnAt;
    //bloqueia a task, quando há um novo bloqueio deve instanciar um novo objeto
    @OneToMany(mappedBy = "task")
    private List<BlockedTask> blocked;
    //create a task
    private OffsetDateTime createdAt;
    private OffsetDateTime completionDate;
    private OffsetDateTime completedDate;
    //start the task
    private OffsetDateTime startDate;

    public Task(TaskCreationDTO taskCreationDTO) {
        var now = OffsetDateTime.now();

        this.name = taskCreationDTO.title();
        this.description = taskCreationDTO.description();
        this.creator = null;
        this.manager = null;
        this.completedBy = null;
        this.status = TaskStatus.CREATED;
        this.completed = false;
        this.cancelled = false;
        this.IsBlocked = false;
        this.taskColumn = null;
        this.taskBoard = null;
        this.enteredCurrentColumnAt = now;
        this.blocked = null;
        this.createdAt = now;
        this.completionDate = null;
        this.completedDate = null;
        this.startDate = null;
    }
    public boolean getCompleted(){
        return completed;
    }
    public boolean getCancelled(){
        return cancelled;
    }
    public boolean IsBlocked(){
        return IsBlocked;
    }
}
