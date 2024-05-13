package com.example.sql_tinh_bhxh_spring.controller;

import com.example.sql_tinh_bhxh_spring.entity.BhxhAgencyEntity;
import com.example.sql_tinh_bhxh_spring.entity.UserEntity;
import com.example.sql_tinh_bhxh_spring.payload.RegisterPayload;
import com.example.sql_tinh_bhxh_spring.service.AuthService;
import com.example.sql_tinh_bhxh_spring.service.BhxhAgencyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class SignUpController {
    private BhxhAgencyService bhxhAgencyService;
    private AuthService authService;

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        List<BhxhAgencyEntity> agencies = bhxhAgencyService.getAllBhxhAgency();
        model.addAttribute("agencies", agencies);
        return "signup"; // Trả về tên view template tương ứng
    }

    @PostMapping("/signup")
    public String postSignup(@ModelAttribute RegisterPayload payload, Model model) {
        UserEntity user = authService.createUser(payload);
        model.addAttribute("message", "success");
        return "redirect:/login";
    }
}
