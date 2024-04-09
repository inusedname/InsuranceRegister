package com.example.sql_tinh_bhxh_spring.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdatePayload {
    private String fullname ;
    private String birthDay ;
    private String password;
    private double salary ;
}
