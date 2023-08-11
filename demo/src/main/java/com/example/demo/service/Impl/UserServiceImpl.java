package com.example.demo.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.userRepository;
import com.example.demo.service.userService;

@Service
public class UserServiceImpl implements userService{
    @Autowired
    userRepository repo;  
    @Autowired
    BCryptPasswordEncoder passwordEncoder; 
                     
    public User findUserbyNameandPassword(String name, String password){
        return repo.findUserbyNameandPassword(name, password);
    }

    public User findbyName(String name){
        return repo.findbyName(name);
    }

    public User saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }
    
}
