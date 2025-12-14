package com.tarefas.service;

import com.tarefas.model.User;
import com.tarefas.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<User> findByLogin(String login) {
        return this.repository.findByLogin(login);
    }

    public Optional<User> findById(UUID id) {
        return this.repository.findById(id);
    }

    public User save(User user) {
        return this.repository.save(user);
    }

    public User Update(User user) {
        return this.repository.save(user);
    }

    public void deleteById(UUID id) {
        this.repository.deleteById(id);
    }


}
