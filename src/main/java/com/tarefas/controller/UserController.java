package com.tarefas.controller;

import com.tarefas.model.User;
import com.tarefas.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {

    UserService service;

    public UserController(UserService taskBoardService) {
        this.service = taskBoardService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable UUID id) {
        return this.service.findById(id)
                .map(tb -> new ResponseEntity<>(tb, HttpStatus.OK))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        this.service.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User taskBoard) {
        return new ResponseEntity<>(this.service.save(taskBoard), HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<User> updateUser(@RequestBody User taskBoard) {
        return new ResponseEntity<>(this.service.Update(taskBoard), HttpStatus.OK);
    }
}
