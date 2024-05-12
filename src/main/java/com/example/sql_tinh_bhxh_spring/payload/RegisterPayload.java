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



    public String getBhxhId() {
        return bhxhId;
    }

    public void setBhxhId(String bhxhId) {
        this.bhxhId = bhxhId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getBhxhAgencyId() {
        return bhxhAgencyId;
    }

    public void setBhxhAgencyId(Long bhxhAgencyId) {
        this.bhxhAgencyId = bhxhAgencyId;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
