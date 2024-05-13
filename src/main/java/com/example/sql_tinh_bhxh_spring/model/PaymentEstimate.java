package com.example.sql_tinh_bhxh_spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.val;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class PaymentEstimate {
    private long deductedAmount;
    private long interestAmount;
    private long baseSalary;
    private LocalDate startDate;
    private LocalDate endDate;

    public long getTotalAmount() {
        int monthGaps = 12 * (endDate.getYear() - startDate.getYear()) + endDate.getMonthValue() - startDate.getMonthValue();
        if (monthGaps < 1) {
            throw new IllegalArgumentException("Invalid month gap:" + startDate + " -> " + endDate);
        }
        return (long) (0.22 * baseSalary * monthGaps) + interestAmount - deductedAmount;
    }

    public short getBasePayment() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
