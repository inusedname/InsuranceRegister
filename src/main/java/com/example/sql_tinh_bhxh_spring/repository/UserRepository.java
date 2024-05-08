package com.example.sql_tinh_bhxh_spring.repository;

import com.example.sql_tinh_bhxh_spring.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByBhxhIdAndPassword(String username, String password);
    boolean existsByBhxhId(String username);
}
