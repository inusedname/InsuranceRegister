package com.example.sql_tinh_bhxh_spring.service;

import com.example.sql_tinh_bhxh_spring.entity.BhxhInvoiceEntity;
import com.example.sql_tinh_bhxh_spring.entity.BhxhSubsEntity;
import com.example.sql_tinh_bhxh_spring.entity.UserEntity;
import com.example.sql_tinh_bhxh_spring.model.PaymentEstimate;
import com.example.sql_tinh_bhxh_spring.repository.BhxhInvoiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class InsuranceServiceTest {
//    @Mock
//    private BhxhInvoiceRepository bhxhInvoiceRepository;
//
//    @Mock
//    private UserService userService;
//
//    @InjectMocks
//    private InsuranceService insuranceService;
//
//    private UserEntity user;
//    private BhxhInvoiceEntity invoice;
//    private BhxhSubsEntity bhxhSubsEntity;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        user = new UserEntity();
//        user.setId(1L);
//        user.setType(UserEntity.Type.HO_NGHEO);
//        user.setCreatedAt(LocalDate.now().minusYears(1));
//        user.setTotalMonthParticipated(100);
//
//        bhxhSubsEntity = new BhxhSubsEntity();
//        user.setBhxhSubsEntity(bhxhSubsEntity);
//
//        invoice = new BhxhInvoiceEntity();
//        invoice.setStartDate(LocalDate.now().minusMonths(5));
//        invoice.setEndDate(LocalDate.now().minusMonths(1));
//        invoice.setUserEntity(user);
//
//        user.setBhxhInvoiceEntities(List.of(invoice));
//    }
//
//    @Test
//    void testGetMonthOptionsLessThan10Years() {
//
//        // Act
//        Map<Integer, String> monthOptions = insuranceService.getMonthOptions(user);
//
//        // Assert
//        assertEquals(4, monthOptions.size());
//        assertEquals("1 tháng", monthOptions.get(1));
//        assertEquals("3 tháng", monthOptions.get(3));
//        assertEquals("6 tháng", monthOptions.get(6));
//        assertEquals("1 năm", monthOptions.get(12));
//    }
//
//    @Test
//    void testGetMonthOptionsMoreThan10Years() {
//        // Arrange
//        user.setTotalMonthParticipated(120); // Exactly 10 years
//
//        // Act
//        Map<Integer, String> monthOptions = insuranceService.getMonthOptions(user);
//
//        // Assert
//        assertEquals(6, monthOptions.size());
//        assertEquals("1 tháng", monthOptions.get(1));
//        assertEquals("3 tháng", monthOptions.get(3));
//        assertEquals("6 tháng", monthOptions.get(6));
//        assertEquals("1 năm", monthOptions.get(12));
//        assertEquals("2 năm", monthOptions.get(24));
//        assertEquals("3 năm", monthOptions.get(36));
//    }
//
//    @Test
//    void testCalculate() { //Kiểm tra trả về một PaymentEstimate với các giá trị chính xác.
//        // Arrange
//        when(userService.findById(anyLong())).thenReturn(Optional.of(user));
//        when(bhxhInvoiceRepository.findById(anyLong())).thenReturn(Optional.of(invoice));
//
//        // Act
//        PaymentEstimate estimate = insuranceService.calculate(user, 5000000L, 12);
//
//        // Assert
//        assertEquals(99000L, estimate.getDeductedAmount());
//        assertEquals(0L, estimate.getInterestAmount());
//        assertEquals(5000000L, estimate.getBaseSalary());
//        assertEquals(LocalDate.now(), estimate.getStartDate());
//        assertEquals(LocalDate.now().plusMonths(12), estimate.getEndDate());
//    }
//
//    @Test
//    void testCalculateDeductedAmount() { //Kiểm tra phương thức trả về số tiền trừ đúng cho loại người dùng.
//        // Act
//        long deductedAmount = insuranceService.calculateDeductedAmount(user);
//
//        // Assert
//        assertEquals(99000L, deductedAmount);
//    }
//
//    @Test
//    void testGetLatestInvoice() { //Kiểm tra trả về hóa đơn mới nhất của người dùng.
//        // Arrange
//        when(userService.findById(anyLong())).thenReturn(Optional.of(user));
//
//        // Act
//        Optional<BhxhInvoiceEntity> latestInvoice = insuranceService.getLatestInvoice(user.getId());
//
//        // Assert
//        assertEquals(invoice, latestInvoice.orElse(null));
//    }
//
////    @Test
////    void testGetPeriodInDebtBhxhSubsEntityNull() {
////        // Arrange
////        user.setBhxhSubsEntity(null);
////
////        // Act
////        int periodInDebt = insuranceService.getPeriodInDebt(user);
////
////        // Assert
////        assertEquals(0, periodInDebt);
////    }
//
//    @Test
//    void testGetPeriodInDebt() {
//
//        // Act
//        int periodInDebt = insuranceService.getPeriodInDebt(user);
//
//        // Assert
//        assertEquals(0, periodInDebt); // Adjust based on the logic
//    }

//    @Test
//    void testGetPeriodInDebtLastInvoiceNull() {
//        // Arrange
////        when(insuranceService.getLatestInvoice(user.getId())).thenReturn(Optional.empty());
//
//        // Act
//        int periodInDebt = insuranceService.getPeriodInDebt(user);
//
//        // Assert
//        assertEquals(0, periodInDebt);
//    }
//
//    @Test
//    void testGetPeriodInDebtExtraMonthsLessThanOrEqualZero() {
//        // Arrange
//        invoice.setEndDate(LocalDate.now());
////        when(insuranceService.getLatestInvoice(user.getId())).thenReturn(Optional.of(invoice));
//
//        // Act
//        int periodInDebt = insuranceService.getPeriodInDebt(user);
//
//        // Assert
//        assertEquals(0, periodInDebt);
//    }
//
//    @Test
//    void testGetPeriodInDebtExtraMonthsGreaterThanZero() {
//        // Arrange
//        invoice.setEndDate(LocalDate.now().minusMonths(5));
//        bhxhSubsEntity.setPlan(6);
//        when(insuranceService.getLatestInvoice(user.getId())).thenReturn(Optional.of(invoice));
//
//        // Act
//        int periodInDebt = insuranceService.getPeriodInDebt(user);
//
//        // Assert
//        assertEquals(1, periodInDebt);
//    }
//
//    @Test
//    void testGetPeriodInDebtExtraMonthsGreaterThanPlan() {
//        // Arrange
//        invoice.setEndDate(LocalDate.now().minusMonths(13));
//        bhxhSubsEntity.setPlan(12);
//        when(insuranceService.getLatestInvoice(user.getId())).thenReturn(Optional.of(invoice));
//
//        // Act
//        int periodInDebt = insuranceService.getPeriodInDebt(user);
//
//        // Assert
//        assertEquals(2, periodInDebt);
//    }
//
//    @Test
//    void testGetPeriodInDebtEdgeCasePlan() {
//        // Arrange
//        invoice.setEndDate(LocalDate.now().minusMonths(12));
//        bhxhSubsEntity.setPlan(12);
//        when(insuranceService.getLatestInvoice(user.getId())).thenReturn(Optional.of(invoice));
//
//        // Act
//        int periodInDebt = insuranceService.getPeriodInDebt(user);
//
//        // Assert
//        assertEquals(1, periodInDebt);
//    }
}
