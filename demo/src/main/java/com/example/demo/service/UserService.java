package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService setupUserDetailsService();

    boolean existsByName(String name);
}
