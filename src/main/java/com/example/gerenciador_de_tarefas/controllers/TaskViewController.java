package com.example.gerenciador_de_tarefas.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.gerenciador_de_tarefas.entities.Task;
import com.example.gerenciador_de_tarefas.services.TaskService;

@Controller
public class TaskViewController {
    @Autowired
    private TaskService taskService;

    //Página para visualizar todas as tarefas
    @GetMapping("/tasks")
    public String viewAllTasks(Model model){
        model.addAttribute("tasks", taskService.getAllTasks());
        return "tasks";
    }

    //Página para visualizar os detalhes de uma tarefa
    @GetMapping("/tasks/{id}")
    public String viewTaskDetails(@PathVariable UUID id, Model model){
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "task-details";
    }
}
