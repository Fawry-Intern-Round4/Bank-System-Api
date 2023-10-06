package com.sakr.banksystemapi.service.impl;

import com.sakr.banksystemapi.entity.User;
import com.sakr.banksystemapi.mapper.DeactivateAccountMapper;
import com.sakr.banksystemapi.mapper.UserMapper;
import com.sakr.banksystemapi.model.DeactivateResponseModel;
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
    private final DeactivateAccountMapper deactivateAccountMapper;

    @Override
    public UserResponseModel getMyProfileInfo() {
        User user = getMyUser();

        return userMapper.toResponse(user);
    }

    @Override
    public DeactivateResponseModel deactivateMyAccount() {
        User user = getMyUser();

        user.setStatus(false);
        userRepository.save(user);

        return deactivateAccountMapper
                .toResponse("You Account Now Is Deactivate, You Can Active It Again By Login");
    }

    @Override
    public User getMyUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return  findUserByEmail(email);
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
