package com.tarefas.controller;

import com.tarefas.dto.request.BlockedTaskCreationDTO;
import com.tarefas.dto.request.ChangeColumnDTO;
import com.tarefas.dto.request.TaskCreationDTO;
import com.tarefas.dto.response.TaskColumnResponseDTO;
import com.tarefas.dto.response.TaskResponseDTO;
import com.tarefas.model.task.Task;
import com.tarefas.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("task")
public class TaskController {

    TaskService service;
    public TaskController(TaskService taskBoardService) {
        this.service = taskBoardService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTask(@PathVariable UUID id) {
        return this.service.findById(id)
                .map(tb -> new ResponseEntity<>(new TaskResponseDTO(tb), HttpStatus.OK))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable UUID id) {
        this.service.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody TaskCreationDTO taskBoard) {
        return new ResponseEntity<>(
                new TaskResponseDTO( this.service.save(taskBoard) ), HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<Task> updateTask(@RequestBody Task taskBoard) {
        return new ResponseEntity<>(this.service.Update(taskBoard), HttpStatus.OK);
    }
    @PatchMapping("/block")
    public ResponseEntity<TaskResponseDTO> updateTask(@RequestBody BlockedTaskCreationDTO blockedTaskDTO) {
        return (blockedTaskDTO.blocked())
                ?
            new ResponseEntity<>(
                    new TaskResponseDTO(
                        this.service.blockTask(
                        blockedTaskDTO.taskId(), blockedTaskDTO.reasonBlocked(), blockedTaskDTO.userId())
                    )
                    , HttpStatus.OK)
                :
            new ResponseEntity<>(
                    new TaskResponseDTO(
                        this.service.unblockTask(
                        blockedTaskDTO.taskId(), blockedTaskDTO.reasonBlocked(), blockedTaskDTO.userId())
                    )
                    , HttpStatus.OK);
    }

    @PatchMapping("/changeColumn")
    public ResponseEntity<TaskColumnResponseDTO> updateColumn(@RequestBody ChangeColumnDTO changeColumnDTO){
        return new ResponseEntity<>(
                new TaskColumnResponseDTO(
                    this.service.changeTaskColumn(changeColumnDTO.taskId(), changeColumnDTO.taskColumnId())
                ),
                HttpStatus.OK);
    }

}
