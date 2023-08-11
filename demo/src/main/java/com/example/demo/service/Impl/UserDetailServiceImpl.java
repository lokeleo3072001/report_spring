package com.example.demo.service.Impl;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.config.UserDetailImpl;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.userRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService  {
    private final userRepository repository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = repository.OptionaluserbyName(username);
        return user.map(UserDetailImpl::new).orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
    }
    
}
