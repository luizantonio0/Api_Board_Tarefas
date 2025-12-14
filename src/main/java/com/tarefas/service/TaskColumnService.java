package com.tarefas.service;

import com.tarefas.model.TaskColumn;
import com.tarefas.repository.TaskColumnRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TaskColumnService {
    private final TaskColumnRepository repository;

    public TaskColumnService(TaskColumnRepository repository) {
        this.repository = repository;
    }


    public Optional<TaskColumn> findById(UUID id) {
        return this.repository.findById(id);
    }

    public TaskColumn save(TaskColumn taskColumn) {
        return this.repository.save(taskColumn);
    }

    public TaskColumn Update(TaskColumn taskColumn) {
        return this.repository.save(taskColumn);
    }

    public void deleteById(UUID id) {
        this.repository.deleteById(id);
    }
}
