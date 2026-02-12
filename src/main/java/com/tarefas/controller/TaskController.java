package com.tarefas.controller;

import com.tarefas.dto.request.BlockedTaskCreationDTO;
import com.tarefas.dto.request.ChangeColumnDTO;
import com.tarefas.dto.request.TaskCreationDTO;
import com.tarefas.dto.response.TaskColumnResponseDTO;
import com.tarefas.dto.response.TaskResponseDTO;
import com.tarefas.model.task.Task;
import com.tarefas.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("task")
@Tag(name = "Task", description = "API REST para gerenciamento de tarefas")
public class TaskController {

    TaskService service;
    public TaskController(TaskService taskBoardService) {
        this.service = taskBoardService;
    }
    @GetMapping("/{id}")
    @Operation(summary = "Retorna uma tarefa")
    @ApiResponse(responseCode = "200", description = "tarefa encontrada")
    @ApiResponse(responseCode = "404", description = "tarefa não encontrada")
    public ResponseEntity<TaskResponseDTO> getTask(@PathVariable UUID id) {
        return this.service.findById(id)
                .map(tb -> new ResponseEntity<>(new TaskResponseDTO(tb), HttpStatus.OK))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma tarefas")
    @ApiResponse(responseCode = "200", description = "tarefa deletada")
    @ApiResponse(responseCode = "404", description = "tarefa não encontrada")
    public void deleteTask(@PathVariable UUID id) {
        this.service.deleteById(id);
    }

    @PostMapping
    @Operation(summary = "Cria uma tarefa")
    @ApiResponse(responseCode = "201", description = "tarefa criada")
    @ApiResponse(responseCode = "400", description = "tarefa não criada")
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody TaskCreationDTO taskBoard) {
        return new ResponseEntity<>(
                new TaskResponseDTO( this.service.save(taskBoard) ), HttpStatus.CREATED);
    }

    @PatchMapping
    @Operation(summary = "Atualiza uma tarefa")
    @ApiResponse(responseCode = "200", description = "tarefa atualizada")
    @ApiResponse(responseCode = "404", description = "tarefa não atualizada")
    public ResponseEntity<Task> updateTask(@RequestBody Task taskBoard) {
        return new ResponseEntity<>(this.service.Update(taskBoard), HttpStatus.OK);
    }
    @PatchMapping("/block")
    @Operation(summary = "Bloqueia uma tarefa")
    @ApiResponse(responseCode = "200", description = "tarefa bloqueada")
    @ApiResponse(responseCode = "404", description = "tarefa não encontrada")
    public ResponseEntity<TaskResponseDTO> blockTask(@RequestBody BlockedTaskCreationDTO blockedTaskDTO) {
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
    @Operation(summary = "Altera a coluna")
    @ApiResponse(responseCode = "200", description = "coluna altera")
    @ApiResponse(responseCode = "404", description = "tarefa não encontrada")
    public ResponseEntity<TaskColumnResponseDTO> updateColumn(@RequestBody ChangeColumnDTO changeColumnDTO){
        return new ResponseEntity<>(
                new TaskColumnResponseDTO(
                    this.service.changeTaskColumn(changeColumnDTO.taskId(), changeColumnDTO.taskColumnId())
                ),
                HttpStatus.OK);
    }

}
