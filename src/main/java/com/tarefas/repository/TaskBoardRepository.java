package com.tarefas.repository;

import com.tarefas.model.TaskBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskBoardRepository extends JpaRepository<TaskBoard, UUID> {
}
