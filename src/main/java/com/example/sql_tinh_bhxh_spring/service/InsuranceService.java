package com.example.sql_tinh_bhxh_spring.service;

import com.example.sql_tinh_bhxh_spring.model.Config;
import com.example.sql_tinh_bhxh_spring.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InsuranceService {
    @Autowired
    private ConfigRepository configRepository;

    public double caculation(int id) {
        Optional<Config> configOptional = configRepository.findById((long) id);
        if (configOptional.isPresent()) {
            Config config = configOptional.get();
            return config.getPercent();
        }
        return 0;
    }
}
