package com.example.sql_tinh_bhxh_spring.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterPayload {
    String bhxhId;
    String fullName;
    String password;
    Long bhxhAgencyId;
    int userType; // 0 -> HO_NGHEO, 1 -> HO_CAN_NGHEO, 2 -> KHAC
}
