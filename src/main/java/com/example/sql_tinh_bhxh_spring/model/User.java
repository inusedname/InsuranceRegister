package com.example.sql_tinh_bhxh_spring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column
    public String username;

    @Column
    public String password;

    @Column
    public String citizen;

    @Column
    public String fullname;

    @Column
    public String phoneNumber;

    @Column
    public String birthday;

    @Column
    public String domestic;

    @Column
    public String email;

    @Column
    public int sex;
}

