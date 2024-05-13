package com.example.sql_tinh_bhxh_spring.controller;

import com.example.sql_tinh_bhxh_spring.entity.BhxhSubsEntity;
import com.example.sql_tinh_bhxh_spring.entity.UserEntity;
import com.example.sql_tinh_bhxh_spring.model.PaymentEstimate;
import com.example.sql_tinh_bhxh_spring.repository.BhxhSubsRepository;
import com.example.sql_tinh_bhxh_spring.service.InsuranceService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

@Controller
@RequestMapping("registration")
@AllArgsConstructor
public class RegistrationController {
    private BhxhSubsRepository bhxhSubsRepository;
    private InsuranceService insuranceService;
    private final Logger logger = Logger.getLogger(RegistrationController.class.getName());

    @GetMapping("")
    public String registration(HttpSession session, Model model) {
        UserEntity user = (UserEntity) session.getAttribute("userEntity");
        if (user == null) {
            logger.warning("User not logged in");
            return "redirect:/login";
        }
        BhxhSubsEntity bhxhSubsEntity = bhxhSubsRepository.findByUserEntity(user);
        if (bhxhSubsEntity != null) {
            model.addAttribute("sub", bhxhSubsEntity);
            PaymentEstimate paymentEstimate = insuranceService.calculate(user, bhxhSubsEntity);
            model.addAttribute("total", paymentEstimate.getTotalAmount());
            model.addAttribute("completed", true);
            return "registration_detail";
        }
        return "registration_new";

        // if user already has registration, return registration_detail
    }

    @GetMapping("calculate")
    public String getCalculate(@RequestParam long baseSalary, @RequestParam int plan, Model model) {
        UserEntity user = new UserEntity();
        long deducted = insuranceService.calculateDeductedAmount(user);
        BhxhSubsEntity fakeBhxhSubsEntity = new BhxhSubsEntity(user, plan, baseSalary, deducted);
        PaymentEstimate paymentEstimate = insuranceService.calculate(user, fakeBhxhSubsEntity);
        model.addAttribute("deductedAmount", deducted);
        model.addAttribute("total", paymentEstimate.getTotalAmount());
        return "registration_calculate";
    }

    @PostMapping("calculate")
    public String postCalculate(
            @RequestParam long baseSalary,
            @RequestParam int plan,
            @RequestParam long deductedAmount,
            HttpSession session
    ) {
        UserEntity user = (UserEntity) session.getAttribute("userEntity");
        BhxhSubsEntity newSub = new BhxhSubsEntity(
                user,
                plan,
                baseSalary,
                deductedAmount
        );
        bhxhSubsRepository.save(newSub);
        return "redirect:/registration";
    }
}
