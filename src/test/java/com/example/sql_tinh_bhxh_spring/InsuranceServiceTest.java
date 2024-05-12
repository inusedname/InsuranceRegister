package com.example.sql_tinh_bhxh_spring;

import com.example.sql_tinh_bhxh_spring.entity.BhxhInvoiceEntity;
import com.example.sql_tinh_bhxh_spring.entity.UserEntity;
import com.example.sql_tinh_bhxh_spring.model.PaymentEstimate;
import com.example.sql_tinh_bhxh_spring.repository.BhxhInvoiceRepository;
import com.example.sql_tinh_bhxh_spring.service.InsuranceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class InsuranceServiceTest {
    @Mock
    private BhxhInvoiceRepository bhxhInvoiceRepository;

    @InjectMocks
    private InsuranceService insuranceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMonthOptions() {
        // Arrange
        UserEntity user = new UserEntity();
        user.setType(UserEntity.Type.HO_NGHEO);

        // Act
        Map<Integer, String> monthOptions = insuranceService.getMonthOptions(user);

        // Assert
        assertEquals(4, monthOptions.size());
        assertEquals("1 tháng", monthOptions.get(1));
        assertEquals("3 tháng", monthOptions.get(3));
        assertEquals("6 tháng", monthOptions.get(6));
        assertEquals("1 năm", monthOptions.get(12));
    }

    @Test
    void testCalculateDeductedAmount() {
        // Arrange
        UserEntity user = new UserEntity();
        user.setType(UserEntity.Type.HO_NGHEO);

        // Act
        long deductedAmount = insuranceService.calculateDeductedAmount(user);

        // Assert
        assertEquals(99000L, deductedAmount);
    }
}
