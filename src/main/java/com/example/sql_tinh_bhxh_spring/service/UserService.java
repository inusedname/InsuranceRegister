package com.example.sql_tinh_bhxh_spring.service;

import com.example.sql_tinh_bhxh_spring.entity.UserEntity;
import com.example.sql_tinh_bhxh_spring.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {
    private UserRepository userRepository;

    public Optional<UserEntity> findById(long id) {
        return userRepository.findById(id);
    }

    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }
}
