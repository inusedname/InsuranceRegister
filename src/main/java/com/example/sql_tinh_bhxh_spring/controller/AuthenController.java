package com.example.sql_tinh_bhxh_spring.controller;

import com.example.sql_tinh_bhxh_spring.entity.UserEntity;
import com.example.sql_tinh_bhxh_spring.service.AuthService;
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
    private AuthService authService;

    @GetMapping
    public String loginPage() {
        return "login";
    }

    @PostMapping
    public String loginUser(@RequestParam("username") String bhxhId, @RequestParam String password, HttpSession session, Model model) {
        UserEntity userEntity = authService.login(bhxhId, password);
        if(userEntity != null) {
            session.setAttribute("userEntity", userEntity);
            System.out.println("Login Success !!");
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Username hoặc password không chính xác");
            return "login";
        }
    }
}

