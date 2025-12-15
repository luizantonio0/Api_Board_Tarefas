package com.tarefas.service;

import com.tarefas.dto.request.TaskBoardCreationDTO;
import com.tarefas.model.TaskBoard;
import com.tarefas.model.User;
import com.tarefas.repository.TaskBoardRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TaskBoardService {
    private final TaskBoardRepository repository;
    private final EntityManager entityManager;

    public TaskBoardService(TaskBoardRepository repository, EntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }


    public Optional<TaskBoard> findById(UUID id) {
        return this.repository.findById(id);
    }

    public TaskBoard save(TaskBoardCreationDTO taskBoardCreationDTO) {
        var authorId = entityManager.getReference(User.class, taskBoardCreationDTO.author());
        TaskBoard taskBoard = new TaskBoard(taskBoardCreationDTO);

        taskBoard.setAuthor(authorId);

        return this.repository.save(taskBoard);
    }

    public TaskBoard Update(TaskBoard taskBoard) {
        return this.repository.save(taskBoard);
    }

    public void deleteById(UUID id) {
        this.repository.deleteById(id);
    }
}
