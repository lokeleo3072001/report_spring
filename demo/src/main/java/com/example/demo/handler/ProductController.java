package com.example.demo.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductController {
    @ExceptionHandler
    public String exception(Exception ex){
        return "error";
    }
}
