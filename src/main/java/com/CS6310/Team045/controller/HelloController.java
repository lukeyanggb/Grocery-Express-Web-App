package com.CS6310.Team045.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @GetMapping(value = "/hello")
    public String hello(Model model) {
        String name = "jiangbei";
        model.addAttribute("name", name);
        return "hello";
    }
}
