package com.tarefas.service;

import com.tarefas.dto.BlockedTaskDTO;
import com.tarefas.model.User;
import com.tarefas.model.task.BlockedTask;
import com.tarefas.model.task.Task;
import com.tarefas.model.task.changes_tasks.ChangeMadeByUser;
import com.tarefas.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Optional<Task> blockTask(String id, BlockedTaskDTO blockedTaskDTO, User user){
        var task = repository.findById(id);
        task.ifPresent(t -> t.setBlocked(new BlockedTask(
                "id",
                blockedTaskDTO.reasonBlocked(),
                null,
                new ChangeMadeByUser(user, OffsetDateTime.now()),
                null,
                true
        )));

        return task;
    }
}
