package com.example.sql_tinh_bhxh_spring.service;

import com.example.sql_tinh_bhxh_spring.entity.UserEntity;
import com.example.sql_tinh_bhxh_spring.repository.UserRepository;
import com.example.sql_tinh_bhxh_spring.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private UserEntity user;

    @BeforeEach
    void setUp() {
        user = new UserEntity();
        user.setId(1L);
        user.setFullname("Nguyen Viet Quang");
        user.setPassword("1");
        user.setBhxhId("1");
    }

    @Test
    void testFindById_UserExists() { //Kiểm tra xem kết quả có tồn tại và đúng như mong đợi không.
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Act
        Optional<UserEntity> result = userService.findById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(user.getId(), result.get().getId());
        assertEquals(user.getFullname(), result.get().getFullname());
    }

    @Test
    void testFindById_UserDoesNotExist() { //Kiểm tra xem kết quả không tồn tại
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Optional<UserEntity> result = userService.findById(1L);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void testSave_User() { //Kiểm tra xem kết quả không null và đúng như mong đợi.
        // Arrange
        when(userRepository.save(any(UserEntity.class))).thenReturn(user);

        // Act
        UserEntity result = userService.save(user);

        // Assert
        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getFullname(), result.getFullname());
    }
}

