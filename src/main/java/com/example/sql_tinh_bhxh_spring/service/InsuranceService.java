package com.example.sql_tinh_bhxh_spring.service;

import com.example.sql_tinh_bhxh_spring.entity.BhxhInvoiceEntity;
import com.example.sql_tinh_bhxh_spring.entity.BhxhSubsEntity;
import com.example.sql_tinh_bhxh_spring.entity.UserEntity;
import com.example.sql_tinh_bhxh_spring.model.PaymentEstimate;
import com.example.sql_tinh_bhxh_spring.repository.BhxhInvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class InsuranceService {
    private BhxhInvoiceRepository bhxhInvoiceRepository;
    private UserService userService;

    public Map<Integer, String> getMonthOptions(UserEntity user) {
        Map<Integer, String> options = new HashMap<>();
        options.put(1, "1 tháng");
        options.put(3, "3 tháng");
        options.put(6, "6 tháng");
        options.put(12, "1 năm");
        if (getAttendedMonths(user) >= 12 * 10) {
            options.put(24, "2 năm");
            options.put(36, "3 năm");
        }
        return options;
    }

    public PaymentEstimate calculate(UserEntity user, int monthsPaying) {
        long deducted = calculateDeductedAmount(user);
        BhxhInvoiceEntity lastInvoice = getLatestInvoice(user.id).orElse(null);
        long debtInterest = lastInvoice != null ? calculateDebtInterestAmount(lastInvoice) : 0L;
        return new PaymentEstimate(
                deducted,
                debtInterest,
                user.getBaseSalary(),
                getStartDate(user),
                getStartDate(user).plusMonths(monthsPaying)
        );
    }

    public long calculateDeductedAmount(UserEntity user) {
        return switch (user.getType()) {
            case HO_NGHEO -> 99000L;
            case HO_CAN_NGHEO -> 82500L;
            case KHAC -> 33000L;
            default -> -1L;
        };
    }

    private long calculateDebtInterestAmount(@NonNull BhxhInvoiceEntity lastInvoice) {
        // TODO: implement this method
        return 0L;
    }

    public void setInvoicePaid(BhxhInvoiceEntity invoice) {
        invoice.setStatus(BhxhInvoiceEntity.Status.PAID);
        bhxhInvoiceRepository.save(invoice);
    }

    public void createBhxhInvoice(PaymentEstimate estimate, UserEntity userEntity) {
        BhxhInvoiceEntity bhxhInvoiceEntity = new BhxhInvoiceEntity(userEntity, estimate);
        bhxhInvoiceRepository.save(bhxhInvoiceEntity);
    }

    public Optional<BhxhInvoiceEntity> getLatestInvoice(long userId) {
        UserEntity userEntity = userService.findById(userId).orElseThrow();
        return userEntity.getBhxhInvoiceEntities().stream()
                .max(Comparator.comparing(BhxhInvoiceEntity::getStartDate));
    }

    private int getAttendedMonths(UserEntity userEntity) {
        return userEntity.getBhxhInvoiceEntities().stream()
                .filter(invoice -> invoice.getStatus() == BhxhInvoiceEntity.Status.PAID)
                .reduce(0, (acc, invoice) -> acc +
                        monthsBetween(invoice.getStartDate(), invoice.getEndDate())
                        , Integer::sum);
    }

    private LocalDate getStartDate(UserEntity userEntity) {
        Optional<BhxhInvoiceEntity> invoice = this.getLatestInvoice(userEntity.id);
        if (invoice.isPresent()) {
            return invoice.get().getStartDate().plusMonths(1);
        } else {
            return LocalDate.now();
        }
    }

    private int monthsBetween(LocalDate start, LocalDate end) {
        return 12 * (end.getYear() - start.getYear()) + end.getMonthValue() - start.getMonthValue();
    }
}
