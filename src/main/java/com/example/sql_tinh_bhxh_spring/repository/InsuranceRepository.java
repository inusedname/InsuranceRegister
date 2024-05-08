package com.example.sql_tinh_bhxh_spring.repository;

import com.example.sql_tinh_bhxh_spring.model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long>  {
    Insurance findFirstByUserIdOrderByIdDesc(int uid);
}
