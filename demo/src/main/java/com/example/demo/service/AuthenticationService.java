package com.example.demo.service;

import com.example.demo.dao.request.SigninRequest;
import com.example.demo.dao.request.SignupRequest;

public interface AuthenticationService {
    void signup(SignupRequest request);

    void signin(SigninRequest request);

    void logOut();

}
