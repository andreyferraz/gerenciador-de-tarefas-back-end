package com.example.gerenciador_de_tarefas.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gerenciador_de_tarefas.entities.Task;
import com.example.gerenciador_de_tarefas.services.TaskService;

@Controller
@RequestMapping("/tasks")
public class TaskViewController {
    @Autowired
    private TaskService taskService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    //Página para visualizar todas as tarefas
    @GetMapping
    public String viewAllTasks(Model model){
        model.addAttribute("tasks", taskService.getAllTasks());
        return "task-list";
    }

    //Página para visualizar os detalhes de uma tarefa
    @GetMapping("/{id}")
    public String viewTaskDetails(@PathVariable UUID id, Model model){
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "task-details";
    }

    // Página para criar uma nova tarefa
    @GetMapping("/new")
    public String showCreateTaskForm(Model model){
        model.addAttribute("task", new Task());
        return "create-task";
    }

    // Método para processar a criação da nova tarefa
    @PostMapping
    public String createTask(@ModelAttribute Task task){
        taskService.saveTask(task);
        return "redirect:/tasks";
    }

    //Página para editar uma tarefa
    @GetMapping("/edit/{id}")
    public String showEditTaskForm(@PathVariable UUID id, Model model){
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "edit-task";
    }

    // Método para atualizar uma tarefa
    @PostMapping("/{id}")
    public String updateTask(@PathVariable UUID id, @ModelAttribute Task task){
        task.setId(id);
        taskService.updateTask(task);
        return "redirect:/tasks";
    }

    // Método para excluir uma tarefa
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable UUID id){
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }
}
