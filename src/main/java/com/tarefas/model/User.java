package com.tarefas.model;

import com.tarefas.model.enums.user.Role;
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
@Table(name = "tb001_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(length = 30, nullable = false)
    private String name;
    @Column(length = 100, unique = true)
    private String email;
    @Column(length = 30, nullable = false,   unique = true)
    private String login;
    @Column(nullable = false)
    private String password;
    @Column(length = 15, nullable = false)
    private Role role;

    @OneToMany(mappedBy = "author")
    private List<TaskBoard> taskBoards;

    @OneToMany(mappedBy = "author")
    private List<TaskColumn> taskColumns;

    @OneToMany(mappedBy = "creator")
    private List<Task> tasks;
    @OneToMany(mappedBy = "manager")
    private List<Task> managedTasks;
}
