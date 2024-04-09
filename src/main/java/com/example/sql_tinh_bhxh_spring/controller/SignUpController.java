package com.example.sql_tinh_bhxh_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignUpController {

    @GetMapping("/signup")
    public String showSignUpForm() {
        return "signup"; // Trả về tên view template tương ứng
    }
}
