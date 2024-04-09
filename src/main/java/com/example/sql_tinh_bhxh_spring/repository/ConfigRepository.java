package com.example.sql_tinh_bhxh_spring.repository;

import com.example.sql_tinh_bhxh_spring.model.Config;
import com.example.sql_tinh_bhxh_spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigRepository extends JpaRepository<Config, Long>  {
}
