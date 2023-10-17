package com.sakr.banksystemapi.controller;

import com.sakr.banksystemapi.model.ResponseModel;
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
    public ResponseEntity<ResponseModel> register(
            @Valid @RequestBody RegisterRequestModel request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseModel
                        .builder()
                        .success(true)
                        .payload(userService.register(request))
                        .build()
                );
    }



    @PostMapping("/authenticate")
    public ResponseEntity<ResponseModel> authenticate(
            @Valid @RequestBody AuthenticationRequestModel request
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseModel
                        .builder()
                        .success(true)
                        .payload(userService.authenticate(request))
                        .build()
                );
    }

}
