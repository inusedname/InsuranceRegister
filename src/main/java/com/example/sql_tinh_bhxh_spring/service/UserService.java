package com.example.sql_tinh_bhxh_spring.service;

import com.example.sql_tinh_bhxh_spring.model.User;
import com.example.sql_tinh_bhxh_spring.payload.RegisterPayload;
import com.example.sql_tinh_bhxh_spring.payload.UpdatePayload;
import com.example.sql_tinh_bhxh_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String registerUser(RegisterPayload payload) {
        if (userRepository.existsByUsernameOrCitizen(payload.getUsername(), payload.getCitizen())) {
            return "Username hoặc CMND/CCCD đã tồn tại";
        }

        return "Thêm dữ liệu thành công";
    }

    public String updateUser(UpdatePayload payload, Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            return "Cập nhật dữ liệu thành công";
        }
        else {
            return "Không tìm thấy user có id: " + id;
        }
    }
}
