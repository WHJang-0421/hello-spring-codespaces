package com.example.hello_codespaces.service;

import com.example.hello_codespaces.domain.Task;
import com.example.hello_codespaces.dto.TaskDto;
import com.example.hello_codespaces.repository.TaskRepository;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public void saveTask(TaskDto taskDto) {
        Task task = Task.builder()
                .title(taskDto.getTitle())
                .finished(taskDto.isFinished())
                .due(taskDto.getDue())
                .build();

        taskRepository.save(task);
    }

    public TaskDto findTaskById(long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                        "task with id " + id + " not found"));

        return TaskDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .finished(task.isFinished())
                .due(task.getDue())
                .build();
    }

    public List<TaskDto> findAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(task -> TaskDto.builder()
                        .id(task.getId())
                        .title(task.getTitle())
                        .finished(task.isFinished())
                        .due(task.getDue())
                        .build())
                .toList();
    }

    public void updateTaskById(long id, TaskDto taskDto) {
        Task task = taskRepository.getReferenceById(id);
        task.updateTask(taskDto.getTitle(), taskDto.isFinished(), taskDto.getDue());
        taskRepository.save(task);
    }

    public void deleteTaskById(long id) {
        taskRepository.deleteById(id);
    }
}
