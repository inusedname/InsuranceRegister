package com.example.sql_tinh_bhxh_spring.controller;

import com.example.sql_tinh_bhxh_spring.model.User;
import com.example.sql_tinh_bhxh_spring.payload.RegisterPayload;
import com.example.sql_tinh_bhxh_spring.payload.UpdatePayload;
import com.example.sql_tinh_bhxh_spring.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/signout")
    public String signOut(HttpSession session) {
        session.invalidate();
        return "redirect:/index";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute RegisterPayload payload, Model model) {
        String message = userService.registerUser(payload);
        model.addAttribute("message", message);
        return "register";
    }

    @PutMapping("/update")
    public String updateUser(@ModelAttribute UpdatePayload payload, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        String message = userService.updateUser(payload, user.getId());
        model.addAttribute("message", message);
        return "register";
    }
}
