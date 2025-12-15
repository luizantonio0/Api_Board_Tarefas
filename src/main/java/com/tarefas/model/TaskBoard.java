package com.tarefas.model;

import com.tarefas.dto.request.TaskBoardCreationDTO;
import com.tarefas.model.task.Task;
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
@Table(name = "tb004_task_board")
public class TaskBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(length = 30, nullable = false)
    private String title;
    @Column(length = 200)
    private String description;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "userAuthorId",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_tb001_tb004")
    )
    private User author;
    @OneToMany(mappedBy = "taskBoard")
    private List<TaskColumn> taskColumns;

    @OneToMany(mappedBy = "taskBoard")
    private List<Task> tasks;

    public TaskBoard(TaskBoardCreationDTO dto) {
        this.title = dto.title();
        this.description = dto.description();
        this.author = null;
        this.taskColumns = null;
        this.tasks = null;
    }
}
