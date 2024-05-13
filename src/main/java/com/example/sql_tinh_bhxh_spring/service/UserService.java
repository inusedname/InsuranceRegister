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

    public void updateSalary(long newSalary, UserEntity userEntity) {
        userEntity.setBaseSalary(newSalary);
        userRepository.save(userEntity);
    }

    public Optional<UserEntity> findById(long id) {
        return userRepository.findById(id);
    }
}
