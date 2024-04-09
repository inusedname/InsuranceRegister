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
    String fullname ;
    String phoneNumber ;
    String birthDay ;
    String username ;
    String password;
    int domestic ;
    long salary ;
    String citizen;
}
