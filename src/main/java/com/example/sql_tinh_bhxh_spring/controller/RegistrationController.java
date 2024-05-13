package com.example.sql_tinh_bhxh_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("registration")
public class RegistrationController {

    @GetMapping("")
    public String registration() {
        return "registration";
    }

    @PostMapping("")
    public String postRegistration() {
        return "";
    }
}
