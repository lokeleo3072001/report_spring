package com.example.demo.service.Impl;

import com.example.demo.dao.request.SigninRequest;
import com.example.demo.dao.request.SignupRequest;
import com.example.demo.entity.Role;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repo;
    private final AuthenticationManager authenticationManager;
    @Override
    public void signup(SignupRequest request) {
        var user = UserEntity.builder().name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        repo.save(user);
    }

    @Override
    public void signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getName(), request.getPassword()));
    }

    @Override
    public void logOut() {
        SecurityContextHolder.clearContext();
    }

}
