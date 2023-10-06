package com.sakr.banksystemapi.service.impl;

import com.sakr.banksystemapi.entity.User;
import com.sakr.banksystemapi.mapper.UserMapper;
import com.sakr.banksystemapi.model.user.UserResponseModel;
import com.sakr.banksystemapi.repository.UserRepository;
import com.sakr.banksystemapi.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseModel getMyProfileInfo() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = findUserByEmail(email);

        return userMapper.toResponse(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User " + email + " Not Found"));
    }

    @Override
    public boolean isUserEmailOrPhoneExist(String email, String phone){
        return userRepository.existsByEmail(email) ||
                userRepository.existsByPhoneNumber(phone);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

}
