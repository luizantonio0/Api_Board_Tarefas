package com.tarefas.service;

import com.tarefas.repository.TaskBoardRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskBoardService {
    private final TaskBoardRepository repository;

    public TaskBoardService(TaskBoardRepository repository) {
        this.repository = repository;
    }
}
