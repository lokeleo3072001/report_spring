package com.example.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.info_product;
import com.example.demo.service.productService;

import jakarta.validation.Valid;



@Controller
public class indexcontroller {
    private static Pattern pattern;
    private Matcher matcher;
    @Autowired
    private productService service;

    private static final String EMAIL_REGEX = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";

    public indexcontroller() {
        pattern = Pattern.compile(EMAIL_REGEX);
    }
    private boolean validate(String regex) {
        matcher = pattern.matcher(regex);
        return matcher.matches();
    }

    @GetMapping("")
    public String home(){
        return "index";
    }

    @GetMapping("validateEmail")
    public String option1(){
        return "validateEmail";
    }

    @PostMapping("validate")
    public String validateEmail(@RequestParam("email") String email, Model model){
        if(!validate(email)){
            model.addAttribute("message", "error");
            return "index";
        }
        System.out.println(email);
        return "success";
    }

    @GetMapping(value="worldclock")
    public String getTime(ModelMap model, @RequestParam(name="city", required = false, defaultValue = "Asia/Ho_Chi_Minh") String city) {
        Date date = new Date();
        TimeZone local = TimeZone.getDefault();
        TimeZone locale = TimeZone.getTimeZone(city);
        long locale_time = date.getTime() +
        (locale.getRawOffset() - local.getRawOffset());
        date.setTime(locale_time);
        model.addAttribute("city",city);
        model.addAttribute("date",date);
        return "time";
    }

    @GetMapping(value = "findAllProduct")
    public String getAllProduct(Model model){
        List<info_product> list = service.findAllProduct();
        model.addAttribute("listProduct", list);
        return "listProduct";
    }

    @PostMapping("changeProduct")
    public String selectProduct(Model model, @RequestParam("id") Long id){
        info_product product = service.findProduct(id);
        model.addAttribute("product", product);
        return "changeProduct";
    }

    @PostMapping("accept")
    public String successChange(@RequestParam("id") Long id, @RequestParam("name") String name){
        service.acceptChangeProduct(id, name);
        return "success";
    }

    @PostMapping("deleteProduct")
    public String deleteProduct(@RequestParam("id") Long id, Model model){
        service.deleteProduct(id);
        List<info_product> list = service.findAllProduct();
        model.addAttribute("listProduct", list);
        return "listProduct";
    }

    @GetMapping("createProduct")
    public String addNewProduct(Model model){
        info_product product = new info_product();

        model.addAttribute("product", product);
        return "createProduct";
    }

    @PostMapping(value = "newProduct", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String newProduct(@Valid @ModelAttribute("info_product") info_product product, BindingResult result, Model model){
        List<info_product> list = service.findAllProduct();
        model.addAttribute("listProduct", list);
        if(result.hasErrors()){
            service.newProduct(product);
        }
        return "listProduct";  
    }
}
