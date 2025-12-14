package com.tarefas.controller;

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
    public ResponseEntity<TaskColumn> getTaskColumn(@PathVariable UUID id) {
        return this.service.findById(id)
                .map(tb -> new ResponseEntity<>(tb, HttpStatus.OK))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public void deleteTaskColumn(@PathVariable UUID id) {
        this.service.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<TaskColumn> createTaskColumn(@RequestBody TaskColumn taskBoard) {
        return new ResponseEntity<>(this.service.save(taskBoard), HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<TaskColumn> updateTaskColumn(@RequestBody TaskColumn taskBoard) {
        return new ResponseEntity<>(this.service.Update(taskBoard), HttpStatus.OK);
    }
}
