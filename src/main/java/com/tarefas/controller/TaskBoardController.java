package com.tarefas.controller;

import com.tarefas.dto.request.TaskBoardCreationDTO;
import com.tarefas.dto.response.TaskBoardResponseDTO;
import com.tarefas.model.TaskBoard;
import com.tarefas.service.TaskBoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("task/board")
@Tag(name = "TaskBoard", description = "API REST para gerenciamento de Board de tarefas")
public class TaskBoardController {
    TaskBoardService service;
    public TaskBoardController(TaskBoardService taskBoardService) {
        this.service = taskBoardService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um board de tarefas")
    @ApiResponse(responseCode = "200", description = "Board de tarefas encontrado")
    @ApiResponse(responseCode = "404", description = "Board não encontrado")
    public ResponseEntity<TaskBoardResponseDTO> getTaskBoard(@PathVariable UUID id) {
        return this.service.findById(id)
                .map(tb -> new ResponseEntity<>(
                        new TaskBoardResponseDTO(tb), HttpStatus.OK))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um board de tarefas")
    @ApiResponse(responseCode = "204", description = "Board de tarefas deletado")
    @ApiResponse(responseCode = "404", description = "Board de tarefas não encontrado")
    public ResponseEntity deleteTaskBoard(@PathVariable UUID id) {
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Cria um novo board de tarefas")
    @ApiResponse(responseCode = "201", description = "Board de tarefas criado")
    @ApiResponse(responseCode = "400", description = "Usuario não cadastrado")
    @PostMapping
    public ResponseEntity<UUID> createTaskBoard(@RequestBody TaskBoardCreationDTO taskBoard) {
        return new ResponseEntity<>(this.service.save(taskBoard).getId(), HttpStatus.CREATED);
    }


    @PatchMapping
    @Operation(summary = "Atualiza um board de tarefas")
    @ApiResponse(responseCode = "200", description = "Board de tarefas atualizado")
    @ApiResponse(responseCode = "400", description = "Board de tarefas atualizado não")
    public ResponseEntity<TaskBoard> updateTaskBoard(@RequestBody TaskBoard taskBoard) {
        return new ResponseEntity<>(this.service.Update(taskBoard), HttpStatus.OK);
    }
}
