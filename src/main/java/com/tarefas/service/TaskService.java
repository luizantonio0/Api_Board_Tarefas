package com.tarefas.service;

import com.tarefas.dto.request.TaskCreationDTO;
import com.tarefas.model.TaskBoard;
import com.tarefas.model.TaskColumn;
import com.tarefas.model.User;
import com.tarefas.model.task.BlockedTask;
import com.tarefas.model.task.Task;
import com.tarefas.model.task.changes_tasks.ChangeMadeByUser;
import com.tarefas.repository.BlockedTaskRepository;
import com.tarefas.repository.TaskColumnRepository;
import com.tarefas.repository.TaskRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {
    private final EntityManager entityManager;
    private final TaskRepository repository;
    private final BlockedTaskRepository blockedTaskRepository;
    private final TaskColumnRepository taskColumnRepository;

    public TaskService(EntityManager entityManager, TaskRepository repository, BlockedTaskRepository blockedTaskRepository, TaskColumnRepository taskColumnRepository) {
        this.entityManager = entityManager;
        this.repository = repository;
        this.blockedTaskRepository = blockedTaskRepository;
        this.taskColumnRepository = taskColumnRepository;
    }

    public Optional<Task> findById(UUID id) {
        return this.repository.findById(id);
    }

    public Task save(TaskCreationDTO taskCreationDTO) {

        var t = new Task(taskCreationDTO);

        t.setManager(
                entityManager.getReference(User.class, taskCreationDTO.manager())
        );
        t.setCreator(
                entityManager.getReference(User.class, taskCreationDTO.creator())
        );
        t.setTaskColumn(
                entityManager.getReference(TaskColumn.class, taskCreationDTO.taskColumn())
        );
        t.setTaskBoard(
                entityManager.getReference(TaskBoard.class, taskCreationDTO.taskBoard())
        );
//ERRO de null
        return this.repository.save(t);
    }

    public Task Update(Task task) {
        return this.repository.save(task);
    }

    public void deleteById(UUID id) {
        this.repository.deleteById(id);
    }

    public Task blockTask(UUID id, String reason, UUID user){
        var opTask = repository.findById(id);
        if(opTask.isEmpty()){
            throw new IllegalStateException("task not found");
        }
        var task = opTask.get();

        if (task.isIsBlocked()){
            throw new IllegalStateException("task is already blocked");
        }
        var bts =  new BlockedTask(
                reason,
                task,
                new ChangeMadeByUser(user,
                        OffsetDateTime.now())
        );

        task.getBlocked().add(
                bts
        );

        task.setIsBlocked(true);
        this.blockedTaskRepository.save(bts);
        this.repository.save(task);

        return task;
    }

    public Task unblockTask(UUID id, String reason, UUID user){
        var opTask = repository.findById(id);
            if(opTask.isEmpty()){
            throw new IllegalStateException("task not found");
        }
        var task = opTask.get();

        if (!task.isIsBlocked()){
        throw new IllegalStateException("task is not blocked");
        }

        var rb = task.getBlocked().stream().max(Comparator.comparing( b -> b.getUserBlocked().date()))
                .orElseThrow(() -> new IllegalStateException("System error, please contact our support, no object found"));

        if (rb.getUserUnblocked() == null) {
            throw new IllegalStateException("System error, please contact our support, illegal object found");
        }
        rb.setReasonUnblocked(reason);
        rb.setUserBlocked(new ChangeMadeByUser(user, OffsetDateTime.now()));
        rb.setBlocked(false);
        task.setIsBlocked(false);

        this.repository.save(task);

        return task;
    }

    public Task changeTaskColumn(UUID taskId, UUID taskColumnId){
        var col = taskColumnRepository.findById(taskColumnId);
        var task = repository.findById(taskId);

        if (col.isEmpty() || task.isEmpty()){
            throw new IllegalStateException("task column not found");
        }

        if (task.get().isIsBlocked()){
            throw new IllegalStateException("task is blocked");
        }

        if (Task.allowTransition().contains(task.get().getStatus())) {
            throw new IllegalStateException("task is not in a valid state to change column: " + task.get().getStatus());
        }

        task.get().getTaskColumn().getTasks().remove(task.get());
        taskColumnRepository.save(task.get().getTaskColumn());

        col.get().getTasks().add(task.get());
        taskColumnRepository.save(task.get().getTaskColumn());

        task.get().setTaskColumn(col.get());
        task.get().setEnteredCurrentColumnAt(OffsetDateTime.now());
        repository.save(task.get());

        return task.get();
    }
}
