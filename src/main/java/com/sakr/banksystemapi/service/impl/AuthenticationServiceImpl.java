package com.sakr.banksystemapi.service.impl;

import com.sakr.banksystemapi.entity.User;
import com.sakr.banksystemapi.exceptions.customexceptions.ResourceNotFoundException;
import com.sakr.banksystemapi.mapper.AuthenticationMapper;
import com.sakr.banksystemapi.model.auth.AuthenticationRequestModel;
import com.sakr.banksystemapi.model.auth.AuthenticationResponseModel;
import com.sakr.banksystemapi.model.auth.RegisterRequestModel;
import com.sakr.banksystemapi.repository.UserRepository;
import com.sakr.banksystemapi.security.JwtService;
import com.sakr.banksystemapi.service.AuthenticationService;
import com.sakr.banksystemapi.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationMapper authenticationMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserInfoService userService;


    @Override
    public AuthenticationResponseModel register(RegisterRequestModel request) {
        if(userRepository.existsByEmail(request.getEmail()) ||
        userRepository.existsByPhoneNumber(request.getPhoneNumber())){
            throw new ResourceNotFoundException("Email or Phone Number is already exists");
        }

        User user = authenticationMapper.toEntity(request);
        userRepository.save(user);

        return authenticationMapper.toAuthResponse(jwtService.generateToken(user));
    }

    @Override
    public AuthenticationResponseModel authenticate(AuthenticationRequestModel request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                )
        );

        User user = userService.findUserByEmail(request.getEmail());

        return authenticationMapper.toAuthResponse(jwtService.generateToken(user));
    }
}
