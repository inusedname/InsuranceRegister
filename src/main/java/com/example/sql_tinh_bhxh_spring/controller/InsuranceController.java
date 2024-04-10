package com.example.sql_tinh_bhxh_spring.controller;

import com.example.sql_tinh_bhxh_spring.model.Insurance;
import com.example.sql_tinh_bhxh_spring.model.User;
import com.example.sql_tinh_bhxh_spring.payload.RegisterInsurance;
import com.example.sql_tinh_bhxh_spring.service.InsuranceService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping( "insurance")
public class InsuranceController {
    @Autowired
    private InsuranceService insuranceService;

    @GetMapping("")
    public String showInsurancePage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        Insurance insurance = this.insuranceService.getByUId(user.getId());
        model.addAttribute("insurance", insurance);
        return "insurance";
    }

    @RequestMapping(
        path = "",
        method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
        produces = {
                MediaType.APPLICATION_ATOM_XML_VALUE,
                MediaType.APPLICATION_JSON_VALUE})
    public String post(RegisterInsurance registerInsurance, HttpSession session) {
        User user = (User) session.getAttribute("user");
        this.insuranceService.save(registerInsurance, user.getId());
        return "redirect:/insurance";
    }
}
