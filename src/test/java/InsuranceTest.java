package com.example.sql_tinh_bhxh_spring.service;

import com.example.sql_tinh_bhxh_spring.entity.UserEntity;
import com.example.sql_tinh_bhxh_spring.model.PaymentEstimate;
import com.example.sql_tinh_bhxh_spring.repository.BhxhInvoiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InsuranceServiceTest {
    
    @Mock
    private BhxhInvoiceRepository bhxhInvoiceRepository;
    
    private InsuranceService insuranceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        insuranceService = new InsuranceService(bhxhInvoiceRepository);
    }

    @Test
    void testGetMonthOptions() {
        UserEntity user = new UserEntity();
        Map<Integer, String> options = insuranceService.getMonthOptions(user);
        assertEquals(4, options.size());
        assertEquals("1 tháng", options.get(1));
        assertEquals("1 tháng", options.get(3));
        assertEquals("6 th�ng", options.get(6));
        assertEquals("1 n?m", options.get(12));
    }

    @Test
    void testCalculate() {
        UserEntity user = new UserEntity();
        user.setBaseSalary(1000L);
        int monthsPaying = 12;
        PaymentEstimate estimate = insuranceService.calculate(user, monthsPaying);
        assertEquals(220, estimate.getBasePayment());
        // Add more assertions as needed
    }
    
    // Add more test methods as needed
}
