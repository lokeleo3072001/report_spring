package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;

@Service
public interface userService {

    User findUserbyNameandPassword(String name, String password);
    User findbyName(String name);
    User saveUser(User user);
}
