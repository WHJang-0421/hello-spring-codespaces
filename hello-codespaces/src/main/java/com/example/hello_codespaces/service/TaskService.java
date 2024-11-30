package com.example.hello_codespaces.service;

import com.example.hello_codespaces.domain.Task;
import com.example.hello_codespaces.dto.TaskDto;
import com.example.hello_codespaces.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void saveTask(TaskDto taskDto) {
        Task task = Task.builder()
            .title(taskDto.getTitle())
            .finished(taskDto.isFinished())
            .due(taskDto.getDue())
            .build();

        taskRepository.save(task);
    }

    public TaskDto findTaskById(long id) {
        Task task = taskRepository.findById(id);

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
        Task task = taskRepository.findById(id);
        task.updateTask(taskDto.getTitle(), taskDto.isFinished(), taskDto.getDue());
        taskRepository.updateById(id, task);
    }

    public void deleteTaskById(long id) {
        taskRepository.deleteById(id);
    }
}
