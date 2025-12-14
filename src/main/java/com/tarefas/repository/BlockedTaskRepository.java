package com.tarefas.repository;

import com.tarefas.model.task.BlockedTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockedTaskRepository extends JpaRepository<BlockedTask, Integer> {
}
