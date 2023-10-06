package com.sakr.banksystemapi.controller;

import com.sakr.banksystemapi.model.auth.AuthenticationRequestModel;
import com.sakr.banksystemapi.model.auth.AuthenticationResponseModel;
import com.sakr.banksystemapi.model.auth.RegisterRequestModel;
import com.sakr.banksystemapi.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<AuthenticationResponseModel> register(
            @Valid @RequestBody RegisterRequestModel request
    ) {
        return new ResponseEntity<>(
                userService.register(request),
                HttpStatus.CREATED
        );
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseModel> authenticate(
            @Valid @RequestBody AuthenticationRequestModel request
    ) {
        return new ResponseEntity<>(
                userService.authenticate(request),
                HttpStatus.OK
        );
    }

}
