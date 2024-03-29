package com.easyworks.smartekp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("login/err401")
    public String err401() {
        return "login/err401";
    }

    @GetMapping("login/err404")
    public String err404() {
        return "login/err404";
    }
    
    @GetMapping("login/err500")
    public String err500() {
        return "login/err500";
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
