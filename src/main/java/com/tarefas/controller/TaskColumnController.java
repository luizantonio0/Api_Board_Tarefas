package com.tarefas.controller;

import com.tarefas.dto.request.TaskColumnCreationDTO;
import com.tarefas.dto.response.TaskColumnResponseDTO;
import com.tarefas.model.TaskColumn;
import com.tarefas.service.TaskColumnService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("task/column")
public class TaskColumnController {

    TaskColumnService service;
    public TaskColumnController(TaskColumnService taskBoardService) {
        this.service = taskBoardService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskColumnResponseDTO> getTaskColumn(@PathVariable UUID id) {
        return this.service.findById(id)
                .map(tb -> new ResponseEntity<>(
                        new TaskColumnResponseDTO (tb)
                        , HttpStatus.OK))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public void deleteTaskColumn(@PathVariable UUID id) {
        this.service.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<UUID> createTaskColumn(@RequestBody TaskColumnCreationDTO taskBoard) {
        return new ResponseEntity<>(this.service.save(taskBoard).getId(), HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<TaskColumn> updateTaskColumn(@RequestBody TaskColumn taskBoard) {
        return new ResponseEntity<>(this.service.Update(taskBoard), HttpStatus.OK);
    }
}
