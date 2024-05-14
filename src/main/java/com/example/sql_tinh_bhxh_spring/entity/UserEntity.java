package com.example.sql_tinh_bhxh_spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
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

    @ManyToOne @JoinColumn(name = "bhxh_agency_id")
    private BhxhAgencyEntity bhxhAgencyEntity;

    private Role role;

    private Type type;

    private int totalMonthParticipated;

    @OneToMany(mappedBy = "userEntity")
    private List<BhxhInvoiceEntity> bhxhInvoiceEntities;

    @OneToOne(mappedBy = "userEntity")
    @Nullable
    private BhxhSubsEntity bhxhSubsEntity;

    private LocalDate createdAt;

    public UserEntity(String bhxhId, String fullname, String password, Type type, BhxhAgencyEntity bhxhAgencyEntity, Role role) {
        this.bhxhId = bhxhId;
        this.fullname = fullname;
        this.password = password;
        this.bhxhAgencyEntity = bhxhAgencyEntity;
        this.role = role;
        this.type = type;
        this.totalMonthParticipated = 0;
        this.createdAt = LocalDate.now();
    }

    public enum Type {
        HO_NGHEO,
        HO_CAN_NGHEO,
        KHAC;

        public String getDisplayName() {
            return switch (this) {
                case HO_NGHEO -> "Hộ nghèo";
                case HO_CAN_NGHEO -> "Hộ cận nghèo";
                case KHAC -> "Khác";
            };
        }
    }

    public enum Role {
        ADMIN,
        USER
    }


}

