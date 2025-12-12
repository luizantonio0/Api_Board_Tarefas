package com.tarefas.repository;

import com.tarefas.model.TaskBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskBoardRepository extends JpaRepository<TaskBoard, String> {
}
