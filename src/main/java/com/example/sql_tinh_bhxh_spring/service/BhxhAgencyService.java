package com.example.sql_tinh_bhxh_spring.service;

import com.example.sql_tinh_bhxh_spring.entity.BhxhAgencyEntity;
import com.example.sql_tinh_bhxh_spring.repository.BhxhAgencyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BhxhAgencyService {
    private final BhxhAgencyRepository bhxhAgencyRepository;

    public List<BhxhAgencyEntity> getAllBhxhAgency() {
        return bhxhAgencyRepository.findAll();
    }
}
