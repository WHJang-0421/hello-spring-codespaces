package com.example.hello_codespaces.repository;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import com.example.hello_codespaces.domain.Task;

import org.springframework.stereotype.Repository;

@Repository
public class MemoryTaskRepository implements TaskRepository {
    private static Map<Long, Task> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public void save(Task task) {
        task.initId(++sequence);
        store.put(sequence, task);
    }

    @Override
    public Task findById(long id) {
        return store.get(id);
    }

    @Override
    public List<Task> findAll() {
        return store.values().stream().toList();
    }

    @Override
    public void updateById(long id, Task task) {
        store.put(id, task);
    }

    @Override
    public void deleteById(long id) {
        store.remove(id);
    }
}
