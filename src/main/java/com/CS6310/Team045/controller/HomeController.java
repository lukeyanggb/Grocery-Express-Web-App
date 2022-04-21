package com.CS6310.Team045.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String root(){
        return "This is the Home Page.";
    }

    @GetMapping("/user")
    public String user(){ return "This is the User Page. ";}

    @GetMapping("/admin")
    public String admin(){
        return "This is Admin Page";
    }
}
