package com.easyworks.smartekp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("login/401")
    public String err401() {
        return "login/401";
    }

    @GetMapping("login/404")
    public String err404() {
        return "login/404";
    }
    
    @GetMapping("login/500")
    public String err500() {
        return "login/500";
    }

    @GetMapping("login/login")
    public String login() {
        return "login/login";
    }
    
    @GetMapping("login/password")
    public String password() {
        return "login/password";
    }    

    @GetMapping("login/register")
    public String register() {
        return "login/register";
    }
    
}
