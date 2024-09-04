package com.example.gerenciador_de_tarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gerenciador_de_tarefas.entities.Task;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

}
