package com.sakr.banksystemapi.controller;

import com.sakr.banksystemapi.model.auth.AuthenticationRequestModel;
import com.sakr.banksystemapi.model.auth.AuthenticationResponseModel;
import com.sakr.banksystemapi.model.auth.RegisterRequestModel;
import com.sakr.banksystemapi.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService userService;

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
