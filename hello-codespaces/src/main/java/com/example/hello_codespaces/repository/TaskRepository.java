package com.example.hello_codespaces.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import com.example.hello_codespaces.domain.Task;

@PreAuthorize("hasRole('ROLE_USER')")
public interface TaskRepository extends JpaRepository<Task, Long> {
    @SuppressWarnings("unchecked")
    @Override
    @PreAuthorize("#task?.user?.name == authentication?.name")
    @NonNull
    Task save(@Param("task") @NonNull Task task);

    @Override
    @PreAuthorize("@taskRepository.findById(#id)?.user?.name == authentication?.name")
    @NonNull
    Optional<Task> findById(@Param("id") @NonNull Long id);

    @Override
    @PreAuthorize("@taskRepository.findById(#id)?.user?.name == authentication?.name")
    void deleteById(@Param("id") @NonNull Long id);
}
