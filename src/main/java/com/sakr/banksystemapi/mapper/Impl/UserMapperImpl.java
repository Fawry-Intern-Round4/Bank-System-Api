package com.sakr.banksystemapi.mapper.Impl;

import com.sakr.banksystemapi.entity.User;
import com.sakr.banksystemapi.entity.enumtypes.RoleType;
import com.sakr.banksystemapi.mapper.UserMapper;
import com.sakr.banksystemapi.model.AuthenticationResponseModel;
import com.sakr.banksystemapi.model.RegisterRequestModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;


@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

    private final PasswordEncoder passwordEncoder;
    @Override
    public User toEntity(RegisterRequestModel requestModel) {
        return User.builder()
                .firstName(requestModel.getFirstName())
                .lastName(requestModel.getLastName())
                .email(requestModel.getEmail())
                .phoneNumber(requestModel.getPhoneNumber())
                .password(passwordEncoder.encode(requestModel.getPassword()))
                .address(requestModel.getAddress())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .userRole(RoleType.USER)
                .status(true)
                .build();
    }

    @Override
    public AuthenticationResponseModel toAuthResponse(String token) {
        return AuthenticationResponseModel
                .builder()
                .token(token)
                .build();
    }
}
