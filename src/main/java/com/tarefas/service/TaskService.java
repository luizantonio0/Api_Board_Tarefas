package com.tarefas.service;

import com.tarefas.dto.request.TaskCreationDTO;
import com.tarefas.model.TaskBoard;
import com.tarefas.model.TaskColumn;
import com.tarefas.model.User;
import com.tarefas.model.enums.task.TaskStatus;
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

    public Optional<Task> blockTask(UUID id, String reason, UUID user){
        var task = repository.findById(id);
        if(task.isEmpty()){
            return Optional.empty();
        }

        if (task.get().isIsBlocked()){
            throw new IllegalStateException("task is already blocked");
        }
        var bts =  new BlockedTask(
                reason,
                task.get(),
                new ChangeMadeByUser(user,
                        OffsetDateTime.now())
        );

        task.get().getBlocked().add(
                bts
        );

        task.get().setIsBlocked(true);
        this.blockedTaskRepository.save(bts);
        this.repository.save(task.get());

        return task;
    }

    public Optional<Task> unblockTask(UUID id, String reason, UUID user){
        var task = repository.findById(id);
            if(task.isEmpty()){
            return Optional.empty();
        }

        if (!task.get().isIsBlocked()){
        throw new IllegalStateException("task is not blocked");
        }

        var rb = task.get().getBlocked().stream().max(Comparator.comparing( b -> b.getUserBlocked().date()))
                .orElseThrow(() -> new IllegalStateException("System error, please contact our support, no object found"));

        if (rb.getUserUnblocked() == null) {
            throw new IllegalStateException("System error, please contact our support, illegal object found");
        }
        rb.setReasonUnblocked(reason);
        rb.setUserBlocked(new ChangeMadeByUser(user, OffsetDateTime.now()));
        rb.setBlocked(false);
        task.get().setIsBlocked(false);

        this.repository.save(task.get());

        return task;
    }

    public TaskColumn changeTaskColumn(UUID id, UUID taskColumnId){
        var col = taskColumnRepository.findById(taskColumnId);
        var task = repository.findById(id);

        if (col.isEmpty() || task.isEmpty()){
            throw new IllegalStateException("task column not found");
        }

        if (task.get().isIsBlocked()){
            throw new IllegalStateException("task is blocked");
        }

        if (task.get().getStatus() == TaskStatus.COMPLETED || task.get().getStatus() == TaskStatus.CANCELLED){
            throw new IllegalStateException("task is " + task.get().getStatus());
        }

        if (col.get().isDefault() )



        if (task.get().getStatus() != TaskStatus.STARTED){

        }

        task.get().setTaskColumn(col.get());
        //TODO ALterar metodo padrao do java para um adicionar personalizado na classe TaskColumn
        col.get().getTasks().add(task.get());
        repository.save(task.get());


        return col.get();

    }
}
