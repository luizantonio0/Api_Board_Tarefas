package com.tarefas.controller;

import com.tarefas.dto.response.UserResponseDTO;
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

    public UserController(UserService service) {
        this.service = service;
    }
    @GetMapping("/login/{id}")
    public ResponseEntity<UserResponseDTO> getUserByLogin(@PathVariable String id) {
        return this.service.findByLogin(id)
                .map(tb -> new ResponseEntity<>(tb, HttpStatus.OK))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
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
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(this.service.save(user), HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return new ResponseEntity<>(this.service.Update(user), HttpStatus.OK);
    }
}
