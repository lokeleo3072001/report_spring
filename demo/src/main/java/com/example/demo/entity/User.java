package com.example.demo.entity;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Validator{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    
    public User() {
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        throw new UnsupportedOperationException("Unimplemented method 'supports'");
    }


    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        String name = user.getName();
        if(name == null || "".equals(name)){
            errors.rejectValue("name", "Không đượC để trống tên");
        }
    }

}
