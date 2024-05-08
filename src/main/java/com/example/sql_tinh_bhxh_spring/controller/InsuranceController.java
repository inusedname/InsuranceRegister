package com.example.sql_tinh_bhxh_spring.controller;

import com.example.sql_tinh_bhxh_spring.entity.UserEntity;
import com.example.sql_tinh_bhxh_spring.service.InsuranceService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InsuranceController {
    @Autowired
    private InsuranceService insuranceService;

    @GetMapping("/insurance")
    public String showInsurancePage(HttpSession session, Model model) {
        UserEntity userEntity = (UserEntity) session.getAttribute("user");


//        long result = (long) (user.getSalary() * pre);

//        model.addAttribute("result", result);
//        model.addAttribute("precent", pre);
//
        return "insurance";
    }
}
