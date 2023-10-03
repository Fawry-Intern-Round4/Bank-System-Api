package com.sakr.banksystemapi.controller;

import com.sakr.banksystemapi.model.AuthenticationRequestModel;
import com.sakr.banksystemapi.model.AuthenticationResponseModel;
import com.sakr.banksystemapi.model.RegisterRequestModel;
import com.sakr.banksystemapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public AuthenticationResponseModel register(
            @RequestBody RegisterRequestModel request
    ) {
        return userService.register(request);
    }


    @PostMapping("/authenticate")
    public AuthenticationResponseModel authenticate(
            @RequestBody AuthenticationRequestModel request
    ) {
        return userService.authenticate(request);
    }

}
