package com.example.sql_tinh_bhxh_spring.service;

import com.example.sql_tinh_bhxh_spring.model.Insurance;
import com.example.sql_tinh_bhxh_spring.payload.RegisterInsurance;
import com.example.sql_tinh_bhxh_spring.repository.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InsuranceService {
    @Autowired
    private InsuranceRepository insuranceRepository;

    public void save(RegisterInsurance registerInsurance, int uid) {
        // Logic validate
        int valDoiTuong = registerInsurance.getDoiTuong();
        int soThang = registerInsurance.getSoThang();
        double thuNhapThang = registerInsurance.getThuNhapThang();
        double BHXHhotro = (valDoiTuong == 0 ? 33000 : (valDoiTuong == 1 ? 82500 : 99000)) * soThang;
        double tinhTienDong = thuNhapThang * 0.22 * soThang - BHXHhotro;

        // Logic Mapper
        registerInsurance.setDoiTuong(valDoiTuong);
        registerInsurance.setSoThang(soThang);
        registerInsurance.setThuNhapThang(thuNhapThang);
        registerInsurance.setTinhTienDong(tinhTienDong);

        Insurance insurance = new Insurance();
        insurance.id = null;
        insurance.tinhTienDong = registerInsurance.getTinhTienDong();
        insurance.doiTuong = registerInsurance.getDoiTuong();
        insurance.soThang = registerInsurance.getSoThang();
        insurance.thuNhapThang = registerInsurance.getThuNhapThang();
        insurance.setUserId(uid);
        this.insuranceRepository.save(insurance);
        return;
    }

    public Insurance getByUId(int userId) {
        Insurance insurance = this.insuranceRepository.findFirstByUserIdOrderByIdDesc(userId);
        return insurance;
    }
}
