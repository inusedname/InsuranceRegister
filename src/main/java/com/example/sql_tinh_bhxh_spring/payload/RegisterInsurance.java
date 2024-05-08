package com.example.sql_tinh_bhxh_spring.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterInsurance {
    int doiTuong;
    double thuNhapThang;
    int soThang;
    double tinhTienDong;
}
