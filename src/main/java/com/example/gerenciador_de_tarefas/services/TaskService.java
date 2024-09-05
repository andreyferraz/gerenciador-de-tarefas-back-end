package com.example.gerenciador_de_tarefas.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gerenciador_de_tarefas.entities.Task;
import com.example.gerenciador_de_tarefas.exceptions.InvalidException;
import com.example.gerenciador_de_tarefas.exceptions.NotFoundException;
import com.example.gerenciador_de_tarefas.repository.TaskRepository;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task saveTask(Task task) {
        if(task.getTitle() == null || task.getTitle().isEmpty()) {
            throw new InvalidException("Title cannot be null or empty");
        }
        if(task.getDescription() == null || task.getDescription().isEmpty()) {
            throw new InvalidException("Description cannot be null or empty");
        }
        return taskRepository.save(task);
    }

    public Task getTaskById(UUID id) {
        return taskRepository.findById(id).orElseThrow(() -> 
        new NotFoundException("Task with ID" + id + "not found" ));
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public void deleteTask(UUID id) {
        if(!taskRepository.existsById(id)) {
            throw new NotFoundException("Task with ID" + id + "not found");
        }
        taskRepository.deleteById(id);
    }

    public Task updateTask(Task task) {
        if(!taskRepository.existsById(task.getId())) {
            throw new NotFoundException("Task with ID" + task.getId() + "not found");
        }
        return taskRepository.save(task);
    }

}
