package com.example.sql_tinh_bhxh_spring.entity;

import jakarta.persistence.*;
import lombok.*;

// Subscription
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BhxhSubsEntity {
    @Id @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;

    @OneToOne @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    private int plan; // chu ky (3, 6, 12 thang)

    private long baseSalary;

    private long deductedAmount;

    public BhxhSubsEntity(UserEntity userEntity, int plan, long baseSalary, long deductedAmount) {
        this.userEntity = userEntity;
        this.plan = plan;
        this.baseSalary = baseSalary;
        this.deductedAmount = deductedAmount;
    }
}
