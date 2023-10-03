package com.sakr.banksystemapi.service.impl;

import com.sakr.banksystemapi.entity.User;
import com.sakr.banksystemapi.exceptions.customexceptions.ResourceNotFoundException;
import com.sakr.banksystemapi.mapper.UserMapper;
import com.sakr.banksystemapi.model.AuthenticationRequestModel;
import com.sakr.banksystemapi.model.AuthenticationResponseModel;
import com.sakr.banksystemapi.model.RegisterRequestModel;
import com.sakr.banksystemapi.repository.UserRepository;
import com.sakr.banksystemapi.security.JwtService;
import com.sakr.banksystemapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User " + email + " Not Found"));
    }


    @Override
    public AuthenticationResponseModel register(RegisterRequestModel request) {
        if(userRepository.existsByEmail(request.getEmail()) ||
        userRepository.existsByPhoneNumber(request.getPhoneNumber())){
            throw new ResourceNotFoundException("Email or Phone Number is already exists");
        }

        User user = userMapper.toEntity(request);
        userRepository.save(user);

        return userMapper.toAuthResponse(jwtService.generateToken(user));
    }

    @Override
    public AuthenticationResponseModel authenticate(AuthenticationRequestModel request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                )
        );

        User user = findUserByEmail(request.getEmail());

        return userMapper.toAuthResponse(jwtService.generateToken(user));
    }
}
