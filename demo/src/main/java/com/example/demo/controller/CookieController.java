package com.example.demo.controller;

import com.example.demo.dao.request.SigninRequest;
import com.example.demo.dao.request.SignupRequest;
import com.example.demo.service.AuthenticationService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.UserEntity;


@Controller
@RequiredArgsConstructor
@SessionAttributes("user")
public class CookieController {

    private final AuthenticationService authenticationService;

    @ModelAttribute("user")
    public UserEntity setUpUserForm() {
        return new UserEntity();
    }

    @GetMapping("/error")
        public String showErrorPage() {
        return "error";
    }


    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping(value="doLogin")
    public String postMethodName(@RequestBody SigninRequest request, Model model) {
        authenticationService.signin(request);
        return "listProduct";
    }

    @GetMapping("register")
    public String register() {
        return "register";
    }

    @PostMapping(value="doRegister")
    public String doRegister(@ModelAttribute SignupRequest request) {
        authenticationService.signup(request);
        return "register";
    }

    @GetMapping("/logout")
    public String logout(){
        authenticationService.logOut();
        return "index";
    }


}
