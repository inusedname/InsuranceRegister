package com.example.sql_tinh_bhxh_spring.service;

import com.example.sql_tinh_bhxh_spring.model.User;
import com.example.sql_tinh_bhxh_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AuthenService {

    @Autowired
    private UserRepository userRepository;

    public User login(String username, String password) {
        Optional<User> user = userRepository.findByUsernameAndPassword(username, password);
        if(user.isEmpty()) {
            return null;
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "entity_not_found"
//            );
        }
        return user.get();
    }
}
