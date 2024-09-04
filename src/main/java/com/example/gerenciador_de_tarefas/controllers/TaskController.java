package com.example.gerenciador_de_tarefas.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.gerenciador_de_tarefas.entities.Task;
import com.example.gerenciador_de_tarefas.services.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    public Task createTask(@RequestBody Task task){
        return taskService.saveTask(task);
    }
}
