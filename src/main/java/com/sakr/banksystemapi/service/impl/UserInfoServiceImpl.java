package com.sakr.banksystemapi.service.impl;

import com.sakr.banksystemapi.entity.User;
import com.sakr.banksystemapi.exceptions.customexceptions.ResourceNotFoundException;
import com.sakr.banksystemapi.mapper.UserMapper;
import com.sakr.banksystemapi.model.user.UserResponseModel;
import com.sakr.banksystemapi.repository.UserRepository;
import com.sakr.banksystemapi.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User " + email + " Not Found"));
    }

    @Override
    public UserResponseModel getMyProfileInfo() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = findUserByEmail(email);

        return userMapper.toResponse(user);
    }

}
