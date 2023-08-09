package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.entity.Counter;

@Controller
@SessionAttributes("counter")
public class SessionController {

    @ModelAttribute("counter")
    public Counter setupCounter(){
        return new Counter();
    }

    @GetMapping("counterPage")
    public String count(@ModelAttribute Counter counter){
        counter.increment();
        return "CounterPage";
    }
}
