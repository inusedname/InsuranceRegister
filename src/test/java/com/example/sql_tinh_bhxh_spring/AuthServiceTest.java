package com.example.sql_tinh_bhxh_spring;

import com.example.sql_tinh_bhxh_spring.entity.BhxhAgencyEntity;
import com.example.sql_tinh_bhxh_spring.entity.UserEntity;
import com.example.sql_tinh_bhxh_spring.payload.RegisterPayload;
import com.example.sql_tinh_bhxh_spring.repository.BhxhAgencyRepository;
import com.example.sql_tinh_bhxh_spring.repository.UserRepository;
import com.example.sql_tinh_bhxh_spring.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private BhxhAgencyRepository bhxhAgencyRepository;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogin_ValidCredentials_ReturnsUserEntity() {
        // Arrange
        String bhxhId = "testId";
        String password = "testPassword";
        UserEntity userEntity = new UserEntity(bhxhId, "Test User", password, UserEntity.Type.HO_NGHEO, new BhxhAgencyEntity(), UserEntity.Role.USER);
        when(userRepository.findByBhxhIdAndPassword(bhxhId, password)).thenReturn(Optional.of(userEntity));

        // Act
        UserEntity result = authService.login(bhxhId, password);

        // Assert
        assertEquals(userEntity, result);
    }

    @Test
    void testLogin_InvalidCredentials_ThrowsResponseStatusException() {
        // Arrange
        String bhxhId = "1";
        String password = "1";
        when(userRepository.findByBhxhIdAndPassword(bhxhId, password)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> authService.login(bhxhId, password));
    }

    @Test
    void testRegisterUser_ValidPayload_ReturnsUserEntity() {
        // Arrange
        RegisterPayload payload = new RegisterPayload("5", "acv dfsdfs", "1", 1L, 0);
        when(userRepository.existsByBhxhId(payload.getBhxhId())).thenReturn(false);
        when(bhxhAgencyRepository.findById(any())).thenReturn(Optional.of(new BhxhAgencyEntity()));

        // Act
        UserEntity result = authService.registerUser(payload);

        // Assert
        assertEquals(payload.getBhxhId(), result.getBhxhId());
        assertEquals(payload.getFullName(), result.getFullname());
        assertEquals(payload.getPassword(), result.getPassword());
        // Add more assertions as needed
    }

    @Test
    void testRegisterUser_DuplicateBhxhId_ThrowsResponseStatusException() {
        // Arrange
        RegisterPayload payload = new RegisterPayload("duplicateId", "Test User", "testPassword", 1L, 0);
        when(userRepository.existsByBhxhId(payload.getBhxhId())).thenReturn(true);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> authService.registerUser(payload));
    }
}
