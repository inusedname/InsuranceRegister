package com.example.sql_tinh_bhxh_spring.service;

import com.example.sql_tinh_bhxh_spring.entity.BhxhAgencyEntity;
import com.example.sql_tinh_bhxh_spring.entity.UserEntity;
import com.example.sql_tinh_bhxh_spring.payload.RegisterPayload;
import com.example.sql_tinh_bhxh_spring.repository.BhxhAgencyRepository;
import com.example.sql_tinh_bhxh_spring.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@AllArgsConstructor
@Service
public class AuthService {
    private UserRepository userRepository;
    private BhxhAgencyRepository bhxhAgencyRepository;

    public UserEntity login(String bhxhId, String password) {
        Optional<UserEntity> user = userRepository.findByBhxhIdAndPassword(bhxhId, password);
        if (user.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity_not_found"
            );
        }
        return user.get();
    }

    public UserEntity createUser(@NonNull RegisterPayload payload) throws ResponseStatusException {
        if (userRepository.existsByBhxhId(payload.getBhxhId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username hoặc CMND/CCCD đã tồn tại");
        }
        BhxhAgencyEntity bhxhAgencyEntity = bhxhAgencyRepository.findById(payload.getBhxhAgencyId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Không tìm thấy cơ quan BHxH"));
        UserEntity newUserEntity = createUserEntity(payload, bhxhAgencyEntity);
        return userRepository.save(newUserEntity);
    }

    private static @NonNull UserEntity createUserEntity(@NonNull RegisterPayload payload, BhxhAgencyEntity bhxhAgencyEntity) {
        UserEntity.Type type = switch (payload.getUserType()) {
            case 0 -> UserEntity.Type.HO_NGHEO;
            case 1 -> UserEntity.Type.HO_CAN_NGHEO;
            case 2 -> UserEntity.Type.KHAC;
            default -> throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unexpected value: " + payload.getUserType());
        };
        return new UserEntity(
                payload.getBhxhId(),
                payload.getFullname(),
                payload.getPassword(),
                type,
                bhxhAgencyEntity,
                UserEntity.Role.USER
        );
    }
}
