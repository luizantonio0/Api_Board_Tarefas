package com.tarefas.service;

import com.tarefas.dto.response.UserResponseDTO;
import com.tarefas.model.User;
import com.tarefas.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<UserResponseDTO> findByLogin(String login) {
        var u = this.repository.findSummaryByLogin(login);

        if(u.isEmpty()){
            return Optional.empty();
        }

        var user = new UserResponseDTO(
                u.get().getId(),
                u.get().getName(),
                u.get().getEmail(),
                u.get().getLogin(),
                u.get().getRole()
        );

        return Optional.of(user);
    }

    public Optional<User> findById(UUID id) {
        return this.repository.findById(id);
    }

    public User save(User user) {
        try {
            return this.repository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException(e.getRootCause());
        }
    }

    public User Update(User user) {
        return this.repository.save(user);
    }

    public void deleteById(UUID id) {
        this.repository.deleteById(id);
    }


}
