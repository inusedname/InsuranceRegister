package com.example.sql_tinh_bhxh_spring.repository;

import com.example.sql_tinh_bhxh_spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndPassword(String username, String password);
    boolean existsByUsernameOrCitizen(String username, String citizen);
}
