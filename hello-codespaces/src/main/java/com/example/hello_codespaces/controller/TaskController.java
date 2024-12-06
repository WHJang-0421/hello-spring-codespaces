package com.example.hello_codespaces.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.hello_codespaces.dto.TaskDto;
import com.example.hello_codespaces.service.TaskService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/api/v1/tasks")
    public List<TaskDto> findAllTasks() {
        return taskService.findAllTasks();
    }

    @PostMapping("/api/v1/tasks")
    public void save(@RequestBody TaskDto taskDto) {
        taskService.saveTask(taskDto);
    }
    
    @GetMapping("/api/v1/tasks/{id}")
    public TaskDto findTaskById(@PathVariable String id) {
        return taskService.findTaskById(id);
    }
    
    @PutMapping("/api/v1/tasks/{id}")
    public void updateTaskById(@PathVariable String id, @RequestBody TaskDto taskDto) {
        taskService.updateTaskById(id, taskDto);
    }

    @DeleteMapping("/api/v1/tasks/{id}")
    public void deleteTaskById(@PathVariable String id) {
        taskService.deleteTaskById(id);
    }
}
