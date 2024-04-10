package com.example.sql_tinh_bhxh_spring.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column
    public long userId;

    @Column
    public int doiTuong;

    @Column
    public double thuNhapThang;

    @Column
    public int soThang;

    @Column
    public double tinhTienDong;
}
