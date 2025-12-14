package com.tarefas.model;

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
    @ManyToOne
    @JoinColumn(name = "taskBoardId")
    private TaskBoard taskBoard;
}
