package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.UserEntity;

@Service
public interface userService {

    UserEntity findUserbyNameandPassword(String name, String password);
    UserEntity findbyName(String name);
    UserEntity saveUser(UserEntity user);
}
