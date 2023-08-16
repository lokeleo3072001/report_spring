package com.example.demo.service;

import com.example.demo.dao.request.SigninRequest;
import com.example.demo.dao.request.SignupRequest;
import org.springframework.validation.BindingResult;

public interface AuthenticationService {
    void signup(SignupRequest request);

    void signin(SigninRequest request);

    void logOut();

}
