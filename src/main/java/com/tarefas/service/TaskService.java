package com.tarefas.service;

import com.tarefas.dto.creationDTO.TaskCreationDTO;
import com.tarefas.model.User;
import com.tarefas.model.task.BlockedTask;
import com.tarefas.model.task.Task;
import com.tarefas.model.task.changes_tasks.ChangeMadeByUser;
import com.tarefas.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Optional<Task> findById(UUID id) {
        return this.repository.findById(id);
    }

    public Task save(TaskCreationDTO taskCreationDTO) {
        return this.repository.save(
                new Task(taskCreationDTO)
        );
    }

    public Task Update(Task task) {
        return this.repository.save(task);
    }

    public void deleteById(UUID id) {
        this.repository.deleteById(id);
    }

    public Optional<Task> blockTask(UUID id, String reason, User user){
        var task = repository.findById(id);
        if(task.isEmpty()){
            return Optional.empty();
        }

        if (task.get().isIsBlocked()){
            throw new IllegalStateException("task is already blocked");
        }

        task.get().getBlocked().add( new BlockedTask(
                reason,
                task.get(),
                new ChangeMadeByUser(user.getId(),
                        OffsetDateTime.now())
                )
        );

        task.get().setIsBlocked(true);
        this.repository.save(task.get());

        return task;
    }

    public Optional<Task> unblockTask(UUID id, String reason, User user){
        var task = repository.findById(id);
            if(task.isEmpty()){
            return Optional.empty();
        }

        if (!task.get().isIsBlocked()){
        throw new IllegalStateException("task is not blocked");
        }


        var rb = task.get().getBlocked().stream().max(Comparator.comparing( b -> b.getUserBlocked().date()))
                .orElseThrow(() -> new IllegalStateException("System error, please contact our support, no object found"));

        if (rb.getUserUnblocked() == null) throw new IllegalStateException("System error, please contact our support, illegal object found");

        rb.setReasonUnblocked(reason);
        rb.setUserBlocked(new ChangeMadeByUser(user.getId(), OffsetDateTime.now()));
        rb.setBlocked(false);
        task.get().setIsBlocked(false);

        this.repository.save(task.get());

        return task;
    }
}
