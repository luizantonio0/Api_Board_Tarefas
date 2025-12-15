package com.tarefas.controller;

import com.tarefas.dto.request.TaskCreationDTO;
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
    public ResponseEntity<Task> getTask(@PathVariable UUID id) {
        return this.service.findById(id)
                .map(tb -> new ResponseEntity<>(tb, HttpStatus.OK))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable UUID id) {
        this.service.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskCreationDTO taskBoard) {
        return new ResponseEntity<>(this.service.save(taskBoard), HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<Task> updateTask(@RequestBody Task taskBoard) {
        return new ResponseEntity<>(this.service.Update(taskBoard), HttpStatus.OK);
    }
}
