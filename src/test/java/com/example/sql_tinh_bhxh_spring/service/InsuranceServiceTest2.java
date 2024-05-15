package com.example.sql_tinh_bhxh_spring.service;

import com.example.sql_tinh_bhxh_spring.entity.BhxhAgencyEntity;
import com.example.sql_tinh_bhxh_spring.entity.BhxhInvoiceEntity;
import com.example.sql_tinh_bhxh_spring.entity.BhxhSubsEntity;
import com.example.sql_tinh_bhxh_spring.entity.UserEntity;
import com.example.sql_tinh_bhxh_spring.model.PaymentEstimate;
import com.example.sql_tinh_bhxh_spring.repository.BhxhInvoiceRepository;
import com.example.sql_tinh_bhxh_spring.service.InsuranceService;
import com.example.sql_tinh_bhxh_spring.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class InsuranceServiceTest2 {

    @Mock
    private BhxhInvoiceRepository bhxhInvoiceRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private InsuranceService insuranceService;

    private UserEntity user;
    private BhxhInvoiceEntity invoice;
    private BhxhSubsEntity bhxhSubsEntity;
    private PaymentEstimate estimate;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        user = new UserEntity();
        user.setId(1L);
        user.setType(UserEntity.Type.HO_NGHEO);
        user.setCreatedAt(LocalDate.now().minusYears(1));
        user.setTotalMonthParticipated(10);

        invoice = new BhxhInvoiceEntity();
        invoice.setStartDate(LocalDate.now().minusMonths(5));
        invoice.setEndDate(LocalDate.now().minusMonths(1));
        invoice.setUserEntity(user);

        bhxhSubsEntity = new BhxhSubsEntity();

        user.setBhxhInvoiceEntities(List.of(invoice));
        estimate = new PaymentEstimate(
                99000L,
                0L,
                5000000L,
                LocalDate.now(),
                LocalDate.now().plusMonths(6)
        );
    }

    @Test
    void testGetMonthOptions() {
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
    void testGetMonthOptionsMoreThan10Years() {
        // Arrange
        user.setTotalMonthParticipated(120); // Exactly 10 years

        // Act
        Map<Integer, String> monthOptions = insuranceService.getMonthOptions(user);

        // Assert
        assertEquals(6, monthOptions.size());
        assertEquals("1 tháng", monthOptions.get(1));
        assertEquals("3 tháng", monthOptions.get(3));
        assertEquals("6 tháng", monthOptions.get(6));
        assertEquals("1 năm", monthOptions.get(12));
        assertEquals("2 năm", monthOptions.get(24));
        assertEquals("3 năm", monthOptions.get(36));
    }

    @Test
    void testCalculate() {
        // Arrange
        when(userService.findById(anyLong())).thenReturn(Optional.of(user));
        when(bhxhInvoiceRepository.findById(anyLong())).thenReturn(Optional.of(invoice));

        // Act
        PaymentEstimate estimate = insuranceService.calculate(user, 5000000L, 12);

        // Assert
        assertEquals(99000L, estimate.getDeductedAmount());
        assertEquals(0L, estimate.getInterestAmount());
        assertEquals(5000000L, estimate.getBaseSalary());
        assertEquals(LocalDate.now(), estimate.getStartDate());
        assertEquals(LocalDate.now().plusMonths(11), estimate.getEndDate());
    }

    @Test
    void testCalculateDeductedAmount() {
        // Act
        long deductedAmount = insuranceService.calculateDeductedAmount(user);

        // Assert
        assertEquals(99000L, deductedAmount);
    }

    @Test
    void testGetLatestInvoice() {
        // Arrange
        when(userService.findById(anyLong())).thenReturn(Optional.of(user));

        // Act
        Optional<BhxhInvoiceEntity> latestInvoice = insuranceService.getLatestInvoice(user.getId());

        // Assert
        assertEquals(invoice, latestInvoice.orElse(null));
    }

    @Test
    void testGetPeriodInDebt() {
        // Act
        int periodInDebt = insuranceService.getPeriodInDebt(user);

        // Assert
        assertEquals(0, periodInDebt); // Adjust based on the logic
    }

    @Test
    void testGetPeriodInDebtBhxhSubsEntityNull() {
        // Arrange
        user.setBhxhSubsEntity(null);

        // Act
        int periodInDebt = insuranceService.getPeriodInDebt(user);

        // Assert
        assertEquals(0, periodInDebt);
    }



//    @Test
//    void testGetPeriodInDebtLastInvoiceNull() {
//        // Arrange
//        when(insuranceService.getLatestInvoice(user.getId())).thenReturn(Optional.empty());
//
//        // Act
//        int periodInDebt = insuranceService.getPeriodInDebt(user);
//
//        // Assert
//        assertEquals(0, periodInDebt);
//    }

    @Test
    void testCreateBhxhInvoice() {
        // Act
        insuranceService.createBhxhInvoice(estimate, user);

        // Assert
        ArgumentCaptor<BhxhInvoiceEntity> invoiceCaptor = ArgumentCaptor.forClass(BhxhInvoiceEntity.class);
        verify(bhxhInvoiceRepository, times(2)).save(invoiceCaptor.capture());
        BhxhInvoiceEntity savedInvoice = invoiceCaptor.getAllValues().get(0);

        assertEquals(user, savedInvoice.getUserEntity());
        assertEquals(estimate.getStartDate(), savedInvoice.getStartDate());
        assertEquals(estimate.getEndDate(), savedInvoice.getEndDate());
        assertEquals(estimate.getBaseSalary(), savedInvoice.getBaseSalary());
        assertEquals(estimate.getDeductedAmount(), savedInvoice.getDeductedAmount());
        assertEquals(estimate.getInterestAmount(), savedInvoice.getInterestAmount());

        // Check if user's total month participated is updated
        assertEquals(16, user.getTotalMonthParticipated()); // 12 + 6 months

        // Verify that userService.save was called with the updated user
        verify(userService).save(user);

        // Verify the second save call is for setting the invoice as paid
        BhxhInvoiceEntity paidInvoice = invoiceCaptor.getAllValues().get(1);
        assertEquals(savedInvoice, paidInvoice);
    }

}
