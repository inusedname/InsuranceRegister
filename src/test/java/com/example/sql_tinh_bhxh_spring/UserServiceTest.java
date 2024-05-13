package com.example.sql_tinh_bhxh_spring;

import com.example.sql_tinh_bhxh_spring.entity.UserEntity;
import com.example.sql_tinh_bhxh_spring.repository.UserRepository;
import com.example.sql_tinh_bhxh_spring.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateSalary() {
        // Arrange
        long newSalary = 1000L; // Giá lương mới
        UserEntity userEntity = new UserEntity(); // Tạo đối tượng UserEntity
        userEntity.setId(1L); // Thiết lập ID cho UserEntity (ví dụ)
        userEntity.setBaseSalary(500L); // Thiết lập mức lương cũ cho UserEntity (ví dụ)

        // Act
        userService.updateSalary(newSalary, userEntity);

        // Assert
        assertEquals(newSalary, userEntity.getBaseSalary()); // Kiểm tra xem mức lương đã được cập nhật chưa
        verify(userRepository, times(1)).save(userEntity); // Kiểm tra xem phương thức save đã được gọi chính xác một lần hay không
    }
}
