package com.sakr.banksystemapi.mapper.Impl;

import com.sakr.banksystemapi.entity.User;
import com.sakr.banksystemapi.mapper.UserMapper;
import com.sakr.banksystemapi.model.user.UserResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {
    @Override
    public UserResponseModel toResponse(User user) {
        return UserResponseModel.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .build();
    }
}
