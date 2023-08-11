package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    // @ModelAttribute("user")
    // public UserEntity setUpUserForm() {
    //     return new UserEntity();
    // }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping(value="doLogin")
    public String postMethodName(@RequestParam("username") String name, @RequestParam("password") String password, Model model) {

        // UserDetails user = uservice.loadUserByUsername(name);
        // Authentication authentication = authenticationManager.authenticate(
        //     new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities())
        // );
        // // Kiểm tra xem người dùng đã đăng nhập thành công hay chưa
        // SecurityContextHolder.getContext().setAuthentication(authentication);

        // model.addAttribute("products",pservice.findAllProduct(PageRequest.of(1, 1)));
        System.out.println("hello");
        return "redirect:/listProduct";
        
    }

    @GetMapping("register")
    public String register(){
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
    

    // @GetMapping("loginUser")
    // public String formLogin( @CookieValue(value = "setUser", defaultValue = "") String setUser, Model model){
    //     Cookie cookie = new Cookie("setUser", setUser);
    //     model.addAttribute("cookieValue", cookie.getValue());
    //     System.out.println(cookie.getValue());
    //     return "formlogin";
    // }

    // @PostMapping("doLogin")
    // public String doLogin(@ModelAttribute("user") User user, Model model,
    // @CookieValue(value = "setUser", defaultValue = "") String setUser, HttpServletResponse response, HttpServletRequest request){
    //     if(user.getName().equals("test")  && user.getPassword().equals("a")){
    //         setUser = user.getName();
    //         Cookie cookie = new Cookie("setUser", setUser);
    //         cookie.setMaxAge(24*60*60);
    //         response.addCookie(cookie);
    //         Cookie[] cookies = request.getCookies();
    //         for(Cookie c: cookies){
    //             System.out.println(c.getName());
    //             if(c.getName().equals("setUser") ) {
    //                 model.addAttribute("cookieValue", c.getValue());
    //                 break;
    //             } else {
    //                 c.setValue("");
    //                 model.addAttribute("cookieValue", c.getValue());
    //             }
    //         }
    //         model.addAttribute("message", "Login success. Welcome ");
    //     } else {
    //         user.setName("");
    //         Cookie cookie = new Cookie("setUser", setUser);
    //         model.addAttribute("cookieValue", cookie.getValue());
    //         model.addAttribute("message", "Login failed. Try again.");
    //     }
    //     return "formlogin";            
    // }
}
