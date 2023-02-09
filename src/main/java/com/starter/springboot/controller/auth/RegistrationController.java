package com.starter.springboot.controller.auth;

import com.starter.springboot.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
@Slf4j
public class RegistrationController {
    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUser(RegistrationForm form){
        log.info(form.toString());
        userRepo.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }

}
