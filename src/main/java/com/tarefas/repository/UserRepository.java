package com.tarefas.repository;

import com.tarefas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByLogin(String login);

    Optional<UserSummary>findSummaryById(UUID id);
    Optional<UserSummary>findSummaryByLogin(String login);

    interface UserSummary{
        UUID getId();
        String getName();
        String getLogin();
        String getEmail();
        String getRole();
        String getPassword();
    }
}
