package com.tarefas.model;

import com.tarefas.model.task.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskColumn {
    private String id;
    private String title;
    private String description;
    private User author;
    private List<Task> tasks;
}
