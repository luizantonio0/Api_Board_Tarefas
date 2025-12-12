package com.tarefas.service;

import com.tarefas.repository.TaskColumnRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskColumnService {
    private final TaskColumnRepository repository;

    public TaskColumnService(TaskColumnRepository repository) {
        this.repository = repository;
    }
}
