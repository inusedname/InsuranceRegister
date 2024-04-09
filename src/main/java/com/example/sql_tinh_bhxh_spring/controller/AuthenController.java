package com.example.sql_tinh_bhxh_spring.controller;

import com.example.sql_tinh_bhxh_spring.model.User;
import com.example.sql_tinh_bhxh_spring.service.AuthenService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class AuthenController {

    @Autowired
    private AuthenService authenService;

    @GetMapping
    public String loginPage() {
        return "login";
    }

    @PostMapping
    public String loginUser(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        User user = authenService.login(username, password);
        if(user != null) {
            session.setAttribute("user", user);
            System.out.println("Login Success !!");
            return "index";
        } else {
            model.addAttribute("error", "Username hoặc password không chính xác");
            return "login";
        }
    }
}

