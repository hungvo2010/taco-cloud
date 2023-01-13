package com.starter.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ErrorController {
    @GetMapping("/error")
    public String error(){
        return "error";
    }
}
