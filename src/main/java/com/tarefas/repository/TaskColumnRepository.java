package com.tarefas.repository;

import com.tarefas.model.TaskColumn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskColumnRepository extends JpaRepository<TaskColumn, UUID> {
}
