package com.example.sql_tinh_bhxh_spring.controller;

import com.example.sql_tinh_bhxh_spring.entity.BhxhInvoiceEntity;
import com.example.sql_tinh_bhxh_spring.entity.BhxhSubsEntity;
import com.example.sql_tinh_bhxh_spring.entity.UserEntity;
import com.example.sql_tinh_bhxh_spring.model.PaymentEstimate;
import com.example.sql_tinh_bhxh_spring.repository.BhxhSubsRepository;
import com.example.sql_tinh_bhxh_spring.service.InsuranceService;
import com.example.sql_tinh_bhxh_spring.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

@Controller
@RequestMapping("registration")
@AllArgsConstructor
public class RegistrationController {
    private BhxhSubsRepository bhxhSubsRepository;
    private InsuranceService insuranceService;
    private UserService userService;
    private final Logger logger = Logger.getLogger(RegistrationController.class.getName());

    @GetMapping("")
    public String registration(HttpSession session, Model model) {
        UserEntity user = userService.findById(((UserEntity) session.getAttribute("userEntity")).getId()).orElseThrow();
        BhxhSubsEntity bhxhSubsEntity = bhxhSubsRepository.findByUserEntity(user);
        if (bhxhSubsEntity != null) {
            // if user already has registration, return registration_detail
            model.addAttribute("sub", bhxhSubsEntity);
            PaymentEstimate paymentEstimate = insuranceService.calculate(user, bhxhSubsEntity.getBaseSalary(), bhxhSubsEntity.getPlan());
            model.addAttribute("total", paymentEstimate.getTotalAmount());
            BhxhInvoiceEntity bhxhInvoiceEntity = insuranceService.getLatestInvoice(user.getId()).orElse(null);
            if (bhxhInvoiceEntity != null) {
                String lastPeriodPay =
                        bhxhInvoiceEntity.getStartDate().format(
                                DateTimeFormatter.ofPattern("MM/yyyy")
                        ) + " - " +
                                bhxhInvoiceEntity.getEndDate().format(
                                        DateTimeFormatter.ofPattern("MM/yyyy")
                                );
                model.addAttribute("lastPeriodPay", lastPeriodPay);
            } else {
                model.addAttribute("lastPeriodPay", "Không có dữ liệu");
            }
            PaymentEstimate estimate = insuranceService.calculate(user, bhxhSubsEntity.getBaseSalary(), bhxhSubsEntity.getPlan());
            String nextPeriodDay =
                    estimate.getStartDate().format(
                            DateTimeFormatter.ofPattern("MM/yyyy")
                    ) + " - " +
                    estimate.getEndDate().format(
                            DateTimeFormatter.ofPattern("MM/yyyy")
                    );
            model.addAttribute("nextPeriodDay", nextPeriodDay);
            model.addAttribute("periodsInDebt", insuranceService.getPeriodInDebt(user));
            return "registration_detail";
        } else {
            // User infos
            String agencyName = user.getBhxhAgencyEntity().getDisplayName();
            String userType = user.getType().getDisplayName();
            int totalMonthParticipated = user.getTotalMonthParticipated();
            model.addAttribute("agencyName", agencyName);
            model.addAttribute("userType", userType);
            model.addAttribute("totalMonthParticipated", totalMonthParticipated);
            return "registration_new";
        }
    }

    @GetMapping("calculate")
    public String getCalculate(@RequestParam long baseSalary, @RequestParam int plan, Model model, HttpSession session) {
        UserEntity user = userService.findById(((UserEntity) session.getAttribute("userEntity")).getId()).orElseThrow();
        long deducted = insuranceService.calculateDeductedAmount(user);
        PaymentEstimate paymentEstimate = insuranceService.calculate(user, baseSalary, plan);
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
