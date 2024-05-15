package com.example.sql_tinh_bhxh_spring.service;

import com.example.sql_tinh_bhxh_spring.entity.BhxhAgencyEntity;
import com.example.sql_tinh_bhxh_spring.entity.UserEntity;
import com.example.sql_tinh_bhxh_spring.payload.RegisterPayload;
import com.example.sql_tinh_bhxh_spring.repository.BhxhAgencyRepository;
import com.example.sql_tinh_bhxh_spring.repository.UserRepository;
import com.example.sql_tinh_bhxh_spring.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private BhxhAgencyRepository bhxhAgencyRepository;

    @InjectMocks
    private AuthService authService;

    private RegisterPayload validPayload;
    private BhxhAgencyEntity validAgency;

    @BeforeEach
    void setUp() {
        validPayload = new RegisterPayload("2", "Nguyen Viet Quang", "1", 1L, 0);
        validAgency = new BhxhAgencyEntity();
    }

    @Test
    void testLogin_UserFound() { //kiem tra khi nguoi dung duoc tim thay
        // Arrange
        UserEntity user = new UserEntity();
        when(userRepository.findByBhxhIdAndPassword("1", "1")).thenReturn(Optional.of(user));

        // Act
        UserEntity result = authService.login("1", "1");

        // Assert
        assertEquals(user, result);
    }

    @Test
    void testLogin_UserNotFound() { // Kiem tra khi nguoi dung khong duoc tim thay
        // Arrange
        when(userRepository.findByBhxhIdAndPassword("1", "2")).thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            authService.login("1", "2");
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    void testCreateUser_ValidPayload_ReturnsUserEntity() { //Kiểm tra khi đăng ký người dùng với payload hợp lệ.
        // Arrange
        when(userRepository.existsByBhxhId(validPayload.getBhxhId())).thenReturn(false);
        when(bhxhAgencyRepository.findById(validPayload.getBhxhAgencyId())).thenReturn(Optional.of(validAgency));

        UserEntity expectedUserEntity = new UserEntity(
                validPayload.getBhxhId(),
                validPayload.getFullname(),
                validPayload.getPassword(),
                UserEntity.Type.HO_NGHEO,
                validAgency,
                UserEntity.Role.USER
        );

        when(userRepository.save(any(UserEntity.class))).thenReturn(expectedUserEntity);

        // Act
        UserEntity result = authService.createUser(validPayload);

        // Assert
        assertNotNull(result, "The created user entity should not be null.");
        assertEquals(validPayload.getBhxhId(), result.getBhxhId());
        assertEquals(validPayload.getFullname(), result.getFullname());
        assertEquals(validPayload.getPassword(), result.getPassword());
    }

    @Test
    void testCreateUser_UsernameExists() { //Kiểm tra khi đăng ký người dùng nhưng username đã tồn tại.
        // Arrange
        when(userRepository.existsByBhxhId(validPayload.getBhxhId())).thenReturn(true);

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            authService.createUser(validPayload);
        });
        assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
    }

    @Test
    void testCreateUser_AgencyNotFound() { //Kiểm tra khi đăng ký người dùng nhưng không tìm thấy cơ quan BHXH.
        // Arrange
        when(userRepository.existsByBhxhId(validPayload.getBhxhId())).thenReturn(false);
        when(bhxhAgencyRepository.findById(validPayload.getBhxhAgencyId())).thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            authService.createUser(validPayload);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }
}
