package com.tarefas.controller;

import com.tarefas.dto.request.TaskBoardCreationDTO;
import com.tarefas.dto.response.TaskBoardResponseDTO;
import com.tarefas.model.TaskBoard;
import com.tarefas.service.TaskBoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("task/board")
public class TaskBoardController {
    TaskBoardService service;
    public TaskBoardController(TaskBoardService taskBoardService) {
        this.service = taskBoardService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskBoardResponseDTO> getTaskBoard(@PathVariable UUID id) {
        return this.service.findById(id)
                .map(tb -> new ResponseEntity<>(
                        new TaskBoardResponseDTO(tb), HttpStatus.OK))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public void deleteTaskBoard(@PathVariable UUID id) {
        this.service.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<UUID> createTaskBoard(@RequestBody TaskBoardCreationDTO taskBoard) {
        return new ResponseEntity<>(this.service.save(taskBoard).getId(), HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<TaskBoard> updateTaskBoard(@RequestBody TaskBoard taskBoard) {
        return new ResponseEntity<>(this.service.Update(taskBoard), HttpStatus.OK);
    }
}
