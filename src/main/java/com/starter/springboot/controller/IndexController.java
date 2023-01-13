package com.starter.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.DispatcherServlet;

@Controller
public class IndexController {
    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
