package com.example.sql_tinh_bhxh_spring.controller;

import com.example.sql_tinh_bhxh_spring.model.Insurance;
import com.example.sql_tinh_bhxh_spring.model.User;
import com.example.sql_tinh_bhxh_spring.payload.RegisterInsurance;
import com.example.sql_tinh_bhxh_spring.service.InsuranceService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller
@RequestMapping("insurance")
public class InsuranceController {
    @Autowired
    private InsuranceService insuranceService;

    @GetMapping("")
    public String showInsurancePage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        Insurance insurance = this.insuranceService.getByUId(user.getId());

        System.out.println(insurance.getDoiTuong() + " " + insurance.getTinhTienDong());
        model.addAttribute("insurance", insurance);
        return "insurance";
    }

    @PostMapping("")
    public String post(@RequestBody() RegisterInsurance registerInsurance, HttpSession session) {
        System.out.println(registerInsurance.getDoiTuong() + " " + registerInsurance.getSoThang());

        User user = (User) session.getAttribute("user");
        this.insuranceService.save(registerInsurance, user.getId());
        return "insurance";
    }
}
