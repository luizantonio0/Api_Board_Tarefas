package com.tarefas.service;

import com.tarefas.dto.request.TaskColumnCreationDTO;
import com.tarefas.model.TaskBoard;
import com.tarefas.model.TaskColumn;
import com.tarefas.model.User;
import com.tarefas.repository.TaskColumnRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TaskColumnService {
    private final TaskColumnRepository repository;
    private final EntityManager entityManager;

    public TaskColumnService(TaskColumnRepository repository, EntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }

    public Optional<TaskColumn> findById(UUID id) {
        return this.repository.findById(id);
    }

    public TaskColumn save(TaskColumnCreationDTO taskColumnDTO) {
        var taskColumn = new TaskColumn(taskColumnDTO);

        var user = entityManager.getReference(User.class, taskColumnDTO.author());
        var board = entityManager.getReference(TaskBoard.class, taskColumnDTO.taskBoard());

        taskColumn.setAuthor(user);
        taskColumn.setTaskBoard(board);

        return this.repository.save(taskColumn);
    }

    public TaskColumn Update(TaskColumn taskColumn) {
        return this.repository.save(taskColumn);
    }

    public void deleteById(UUID id) {
        this.repository.deleteById(id);
    }
}
