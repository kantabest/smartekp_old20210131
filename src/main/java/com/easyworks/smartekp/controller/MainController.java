package com.easyworks.smartekp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping
    public String index() {
        return "index";
    }
    
    @GetMapping("index")
    public String index2() {
        return "index";
    }

    @GetMapping("layout-static")
    public String layout_static() {
        return "layout-static";
    }

}