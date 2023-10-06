package com.sakr.banksystemapi.service.impl;

import com.sakr.banksystemapi.entity.User;
import com.sakr.banksystemapi.mapper.AuthenticationMapper;
import com.sakr.banksystemapi.model.auth.AuthenticationRequestModel;
import com.sakr.banksystemapi.model.auth.AuthenticationResponseModel;
import com.sakr.banksystemapi.model.auth.RegisterRequestModel;
import com.sakr.banksystemapi.security.JwtService;
import com.sakr.banksystemapi.service.AuthenticationService;
import com.sakr.banksystemapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AuthenticationMapper authenticationMapper;


    @Override
    public AuthenticationResponseModel register(RegisterRequestModel request) {
        if (
                userService.isUserEmailOrPhoneExist(
                        request.getEmail(), request.getPhoneNumber()
                )
        ) {
            throw new IllegalArgumentException("Email or Phone Number is already exists");
        }

        User user = authenticationMapper.toEntity(request);
        userService.saveUser(user);

        return authenticationMapper
                .toAuthResponse(jwtService.generateToken(user));
    }

    @Override
    public AuthenticationResponseModel authenticate(AuthenticationRequestModel request) {
        User user = userService.findUserByEmail(request.getEmail());
        if (!user.getStatus()) {
            enableAccountOnLogin(user);
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                )
        );

        return authenticationMapper.toAuthResponse(jwtService.generateToken(user));
    }

    private void enableAccountOnLogin(User user) {
        user.setStatus(true);
        userService.saveUser(user);
    }
}
