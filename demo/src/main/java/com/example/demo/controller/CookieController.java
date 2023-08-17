package com.example.demo.controller;

import com.example.demo.dao.request.SigninRequest;
import com.example.demo.dao.request.SignupRequest;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.UserEntity;


@Controller
@RequiredArgsConstructor
@SessionAttributes("user")
public class CookieController {

    private final AuthenticationService authenticationService;
    private final UserService service;

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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)){
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping(value="doLogin")
    public String postMethodName(@RequestBody SigninRequest request) {

        authenticationService.signin(request);
        return "listProduct";
    }

    @GetMapping("register")
    public String register() {
        return "register";
    }

    @PostMapping(value="doRegister")
    public String doRegister(@Valid @ModelAttribute SignupRequest request, BindingResult result) {
        if(result.hasErrors() || service.existsByName(request.getName())){
            return "register";
        } else {
            authenticationService.signup(request);
            return "redirect:/login";
        }

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        authenticationService.logOut();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    break;
                }
            }
        }
        SecurityContextHolder.clearContext();
        return "index";
    }

}
