package com.example.hello_codespaces.repository;

import java.util.List;
import com.example.hello_codespaces.domain.Task;

public interface TaskRepository {
    void save(Task task);
    Task findById(long id); 
    List<Task> findAll();
    void updateById(long id, Task task);
    void deleteById(long id);
}
