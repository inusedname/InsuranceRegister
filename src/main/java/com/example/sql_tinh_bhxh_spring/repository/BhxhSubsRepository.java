package com.example.sql_tinh_bhxh_spring.repository;

import com.example.sql_tinh_bhxh_spring.entity.BhxhSubsEntity;
import com.example.sql_tinh_bhxh_spring.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

public interface BhxhSubsRepository extends JpaRepository<BhxhSubsEntity, Long> {
    @Nullable BhxhSubsEntity findByUserEntity(UserEntity user);
}
