package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.entity.User;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@SessionAttributes("user")
public class CookieController {

    @ModelAttribute("user")
    public User setUpUserForm() {
        return new User();
    }

    @GetMapping("loginUser")
    public String formLogin( @CookieValue(value = "setUser", defaultValue = "") String setUser, Model model){
        Cookie cookie = new Cookie("setUser", setUser);
        model.addAttribute("cookieValue", cookie.getValue());
        System.out.println(cookie.getValue());
        return "formlogin";
    }

    @PostMapping("doLogin")
    public String doLogin(@ModelAttribute("user") User user, Model model,
    @CookieValue(value = "setUser", defaultValue = "") String setUser, HttpServletResponse response, HttpServletRequest request){
        if(user.getName().equals("test")  && user.getPassword().equals("a")){
            setUser = user.getName();
            Cookie cookie = new Cookie("setUser", setUser);
            cookie.setMaxAge(24*60*60);
            response.addCookie(cookie);
            Cookie[] cookies = request.getCookies();
            for(Cookie c: cookies){
                System.out.println(c.getName());
                if(c.getName().equals("setUser") ) {
                    model.addAttribute("cookieValue", c.getValue());
                    break;
                } else {
                    c.setValue("");
                    model.addAttribute("cookieValue", c.getValue());
                }
            }
            model.addAttribute("message", "Login success. Welcome ");
        } else {
            user.setName("");
            Cookie cookie = new Cookie("setUser", setUser);
            model.addAttribute("cookieValue", cookie.getValue());
            model.addAttribute("message", "Login failed. Try again.");
        }
        return "formlogin";            
    }
}
