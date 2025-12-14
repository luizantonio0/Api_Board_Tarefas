package com.tarefas.service;

import com.tarefas.model.TaskBoard;
import com.tarefas.repository.TaskBoardRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TaskBoardService {
    private final TaskBoardRepository repository;

    public TaskBoardService(TaskBoardRepository repository) {
        this.repository = repository;
    }


    public Optional<TaskBoard> findById(UUID id) {
        return this.repository.findById(id);
    }

    public TaskBoard save(TaskBoard taskBoard) {
        return this.repository.save(taskBoard);
    }

    public TaskBoard Update(TaskBoard taskBoard) {
        return this.repository.save(taskBoard);
    }

    public void deleteById(UUID id) {
        this.repository.deleteById(id);
    }
}
