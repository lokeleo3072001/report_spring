package com.example.demo.service.Impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.userRepository;

@Service
public class UserServiceImpl implements UserDetailsService{
    userRepository repo;  
    @Autowired
    BCryptPasswordEncoder passwordEncoder; 

    public UserEntity findbyName(String name){
        return repo.findbyName(name);
    }

    public UserEntity saveUser(UserEntity user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(UserEntity userEntity) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        // Lấy và thêm vai trò của người dùng vào danh sách authorities
        authorities.add(new SimpleGrantedAuthority("ROLE_USER")); // Ví dụ: ROLE_USER

        return authorities;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = repo.findbyName(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new User(userEntity.getName(), userEntity.getPassword(), getAuthorities(userEntity));
    }
    
}
