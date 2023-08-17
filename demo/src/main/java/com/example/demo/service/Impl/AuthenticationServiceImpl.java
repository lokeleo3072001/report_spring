package com.example.demo.service.Impl;

import com.example.demo.dao.request.SigninRequest;
import com.example.demo.dao.request.SignupRequest;
import com.example.demo.entity.Role;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repo;
    private final AuthenticationManager authenticationManager;
    @Override
    public void signup(SignupRequest request) {
        UserEntity user = UserEntity.builder().name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        repo.save(user);
    }

    @Override
    public void signin(SigninRequest request) {
        SecurityContextHolder.getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken(request.getName(), request.getPassword()));
    }

    @Override
    public void logOut() {
        SecurityContextHolder.clearContext();
    }

}
