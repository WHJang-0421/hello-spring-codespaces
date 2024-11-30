package com.example.hello_codespaces.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hello_codespaces.domain.Task;

public interface HibernateTaskRepository extends JpaRepository<Task, Long> {
    
}
