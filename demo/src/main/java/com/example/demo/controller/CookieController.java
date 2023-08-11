package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.productService;
import com.example.demo.service.Impl.UserServiceImpl;

@Controller
@SessionAttributes("user")
public class CookieController {

    @Autowired
    UserServiceImpl uservice;
    @Autowired
    productService pservice;
    @Autowired
    private AuthenticationManager authenticationManager; 

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
    public String postMethodName(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "redirect:/listProduct";
        } catch (AuthenticationException e) {
            return "redirect:/error";
        }
    }

    @GetMapping("register")
    public String register() {
        return "register";
    }

    @PostMapping(value="doRegister")
    public String doRegister(@ModelAttribute("user") UserEntity user, Model model) {
        System.out.println(user.getName() + " " + user.getPassword());
        if(uservice.findbyName(user.getName()) == null){
            uservice.saveUser(user);
            return "login";
        }
        return "register";
    }

}
