package com.example.sql_tinh_bhxh_spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String bhxhId;

    public String fullname;

    public String password;

    private Long baseSalary;

    @ManyToOne @JoinColumn(name = "bhxh_agency_id")
    private BhxhAgencyEntity bhxhAgencyEntity;

    private Role role;

    private Type type;

    @OneToMany(mappedBy = "userEntity")
    private List<BhxhInvoiceEntity> bhxhInvoiceEntities;

    public UserEntity(String bhxhId, String fullname, String password, Type type, BhxhAgencyEntity bhxhAgencyEntity, Role role) {
        this.bhxhId = bhxhId;
        this.fullname = fullname;
        this.password = password;
        this.bhxhAgencyEntity = bhxhAgencyEntity;
        this.role = role;
        this.type = type;
    }

    public enum Type {
        HO_NGHEO,
        HO_CAN_NGHEO,
        KHAC
    }

    public enum Role {
        ADMIN,
        USER
    }


}

