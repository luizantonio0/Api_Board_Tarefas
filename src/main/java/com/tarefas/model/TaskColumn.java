package com.tarefas.model;

import com.tarefas.dto.request.TaskColumnCreationDTO;
import com.tarefas.model.task.Task;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb003_task_column")
public class TaskColumn {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(length = 30, nullable = false)
    private String title;
    @Column(length = 200)
    private String description;
    @ManyToOne
    @JoinColumn(name = "userAuthorId")
    private User author;
    @OneToMany(mappedBy = "taskColumn")
    private List<Task> tasks;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "taskBoardId",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_tb004_tb003")
    )
    private TaskBoard taskBoard;

    public TaskColumn(TaskColumnCreationDTO taskColumnCreationDTO) {
        this.title = taskColumnCreationDTO.title();
        this.description = taskColumnCreationDTO.description();
        this.author = null;
        this.tasks = null;
        this.taskBoard = null;
    }
}
