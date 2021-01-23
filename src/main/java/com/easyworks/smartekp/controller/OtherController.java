package com.easyworks.smartekp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OtherController {
    
    @GetMapping("other/voteList")
    public String voteList() {
        return "other/voteList";
    }

    @GetMapping("other/pdsList")
    public String pdsList() {
        return "other/pdsList";
    }
    
    @GetMapping("other/addressList")
    public String addressList() {
        return "other/addressList";
    }

    @GetMapping("other/connectionList")
    public String connectionList() {
        return "other/connectionList";
    }

    @GetMapping("other/clupList")
    public String clupList() {
        return "other/clupList";
    }    

    @GetMapping("other/blogList")
    public String blogList() {
        return "other/blogList";
    }    
}
