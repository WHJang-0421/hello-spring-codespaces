package com.example.hello_codespaces.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.hello_codespaces.domain.User;
import com.example.hello_codespaces.repository.UserRepository;

@Component
public class SpringDataJpaUserDetailsService implements UserDetailsService {
    private final UserRepository repository;

    public SpringDataJpaUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = this.repository.findByName(name);
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getRoles()));
    }
}
