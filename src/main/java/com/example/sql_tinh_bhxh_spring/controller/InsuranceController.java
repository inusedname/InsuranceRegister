package com.example.sql_tinh_bhxh_spring.controller;

import com.example.sql_tinh_bhxh_spring.entity.UserEntity;
import com.example.sql_tinh_bhxh_spring.model.PaymentEstimate;
import com.example.sql_tinh_bhxh_spring.service.InsuranceService;
import com.example.sql_tinh_bhxh_spring.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/insurance")
public class InsuranceController {
    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String getInsurance() {
        return "redirect:/insurance/pay";
    }

    @GetMapping("/pay")
    public String getPay(Model model, HttpSession session) {
        UserEntity userEntity = userService.findById(((UserEntity) session.getAttribute("userEntity")).getId()).orElseThrow();
        model.addAttribute("customerName", userEntity.getFullname());
        model.addAttribute("customerBhxhId", userEntity.getBhxhId());
        model.addAttribute("totalMonthParticipated", userEntity.getTotalMonthParticipated());

        // check if user have already subscribed
        if (userEntity.getBhxhSubsEntity() == null) {
            return "redirect:/registration";
        }
        PaymentEstimate paymentEstimate = insuranceService.calculate(
                userEntity,
                userEntity.getBhxhSubsEntity().getBaseSalary(),
                userEntity.getBhxhSubsEntity().getPlan()
        );
        String textPeriodPaying =
                paymentEstimate.getStartDate().format(
                        DateTimeFormatter.ofPattern("MM/yyyy")
                ) + " - " +
                paymentEstimate.getEndDate().format(
                        DateTimeFormatter.ofPattern("MM/yyyy")
                );
        model.addAttribute("total", paymentEstimate.getTotalAmount());
        model.addAttribute("textPeriodPaying", textPeriodPaying);
        return "insurance_make_payment";
    }

    @PostMapping("/pay")
    public String postPay(HttpSession session) {
        UserEntity userEntity = userService.findById(((UserEntity) session.getAttribute("userEntity")).getId()).orElseThrow();
        // check if user have already subscribed
        if (userEntity.getBhxhSubsEntity() == null) {
            return "redirect:/registration";
        }
        PaymentEstimate paymentEstimate = insuranceService.calculate(
                userEntity,
                userEntity.getBhxhSubsEntity().getBaseSalary(),
                userEntity.getBhxhSubsEntity().getPlan()
        );
        insuranceService.createBhxhInvoice(paymentEstimate, userEntity);
        return "insurance_pay_success";
    }
}
