package com.starter.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping(value = "/home")
    @ModelAttribute(name = "testModel")
    public String home() {
        // interpreted as the logical name of a view.
        return "home";
    }
}
