package com.example.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Email;
import com.example.demo.entity.Product;
import com.example.demo.service.productService;


@Controller
public class HomeController {
    private static Pattern pattern;
    private Matcher matcher;
    
    @Autowired
    private productService service;

    private static final String EMAIL_REGEX = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";

    public HomeController() {
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

    // @GetMapping(value = "findAllProduct")
    // public String getAllProduct(Model model,@PageableDefault(value = 5) Pageable pageable){
    //     Page<Products> list = service.findAllProduct(pageable);
    //     model.addAttribute("products", list);
    //     return "listProduct";
    // }

    // @GetMapping(value = "findAllProduct")
    // public String getAllProduct(Model model){
    //     Pageable pageable = PageRequest.of(1, 5);
    //     List<Product> list = service.findAllProduct(pageable);
    //     model.addAttribute("products", list);
    //     return "listProduct";
    // }

    // @PostMapping(value="search")
    // public String searchProduct(Model model, @RequestParam("name") String name, Pageable pageable){
    //     List<Product> products = service.findProductbyname(name);
    //     model.addAttribute("listProduct", products);
    //     return "listProduct";
    // }

    @PostMapping("changeProduct")
    public String selectProduct(Model model, @RequestParam("id") Long id){
        Product product = service.findProduct(id);
        model.addAttribute("product", product);
        return "changeProduct";
    }

    @PostMapping("accept")
    public String successChange(@RequestParam("id") Long id, @RequestParam("name") String name){
        service.acceptChangeProduct(id, name);
        return "success";
    }

    // @PostMapping("deleteProduct")
    // public String deleteProduct(@RequestParam("id") Long id, Model model, @PageableDefault(value = 5) Pageable pageable){
    //     service.deleteProduct(id);
    //     Page<Product> list = service.findAllProduct(pageable);
    //     model.addAttribute("listProduct", list);
    //     return "listProduct";
    // }

    @GetMapping("createProduct")
    public String addNewProduct(Model model){
        Product product = new Product();

        model.addAttribute("product", product);
        return "createProduct";
    }

    // @PostMapping(value = "newProduct", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    // public String newProduct(@Valid @ModelAttribute("Info_product") Products product, BindingResult result, Model model, @PageableDefault(value = 5) Pageable pageable){
    //     if(!result.hasErrors()){
    //         service.newProduct(product);
    //     }
    //     Page<Products> list = service.findAllProduct(pageable);
    //     model.addAttribute("listProduct", list);
    //     return "listProduct";  
    // }

    @GetMapping(value = "saveEmail")
    public String saveInfoEmail(Model model){
        model.addAttribute("email", new Email());
        model.addAttribute("languages", new String[]{"English", "Vietnamese", "Japanese", "Chinese"});
        model.addAttribute("sizes", new int[]{5,10,15,20,25,50,100});
        return "formInfoEmail";
    }

    @PostMapping("saveInfo")
    public String saveInfoEmail(@ModelAttribute("email") Email email, Model model){
        model.addAttribute("email", email);
        return "infoEmail";
    }
}
