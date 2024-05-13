package com.example.sql_tinh_bhxh_spring.controller;

import com.example.sql_tinh_bhxh_spring.entity.UserEntity;
import com.example.sql_tinh_bhxh_spring.payload.RegisterPayload;
import com.example.sql_tinh_bhxh_spring.service.AuthService;
import com.example.sql_tinh_bhxh_spring.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @GetMapping("/signout")
    public String signOut(HttpSession session) {
        session.invalidate();
        return "redirect:/index";
    }

//    @PutMapping("/update")
//    public String updateUser(@ModelAttribute UpdatePayload payload, HttpSession session, Model model) {
//        UserEntity userEntity = (UserEntity) session.getAttribute("user");
//        String message = userService.updateUser(payload, userEntity.getId());
//        model.addAttribute("message", message);
//        return "register";
//    }
}
