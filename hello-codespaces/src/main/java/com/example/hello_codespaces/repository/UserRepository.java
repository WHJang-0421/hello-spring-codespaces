package com.example.hello_codespaces.repository;

import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.hello_codespaces.domain.User;

@RepositoryRestResource(exported = false)
public interface UserRepository extends Repository<User, Long> {
    User save(User user);

    User findByName(String name);
}
