package com.example.sql_tinh_bhxh_spring.entity;

import com.example.sql_tinh_bhxh_spring.model.PaymentEstimate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class BhxhInvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
    private Status status = Status.UNPAID;

    private long deductedAmount;
    private long interestAmount;
    private long baseSalary;
    private long totalAmount;
    private LocalDate startDate;
    private LocalDate endDate;

    public BhxhInvoiceEntity(UserEntity userEntity, PaymentEstimate estimate) {
        this.userEntity = userEntity;
        this.deductedAmount = estimate.getDeductedAmount();
        this.interestAmount = estimate.getInterestAmount();
        this.baseSalary = estimate.getBaseSalary();
        this.totalAmount = estimate.getTotalAmount();
        this.startDate = estimate.getStartDate();
        this.endDate = estimate.getEndDate();
    }

    public enum Status {
        UNPAID,
        PAID
    }
}
