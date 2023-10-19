package com.sakr.banksystemapi.controller;

import com.sakr.banksystemapi.model.ResponseModel;
import com.sakr.banksystemapi.model.account.DeactivateResponseModel;
import com.sakr.banksystemapi.model.user.UserResponseModel;
import com.sakr.banksystemapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping()
    public ResponseEntity<ResponseModel> getUserInfo() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseModel
                        .builder()
                        .success(true)
                        .payload(userService.getMyProfileInfo())
                        .build()
                );
    }

    @PutMapping
    public ResponseEntity<ResponseModel> deactivateMyUser(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseModel
                        .builder()
                        .success(true)
                        .payload(userService.deactivateMyUser())
                        .build()
                );
    }



}
