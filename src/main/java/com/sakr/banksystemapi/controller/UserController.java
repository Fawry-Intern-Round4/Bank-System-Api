package com.sakr.banksystemapi.controller;

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
    public ResponseEntity<UserResponseModel> getUserInfo() {
        return new ResponseEntity<>(
                userService.getMyProfileInfo(),
                HttpStatus.OK
        );
    }

    @DeleteMapping
    public ResponseEntity<DeactivateResponseModel> deactivateMyUser(){
        return new ResponseEntity<>(
                userService.deactivateMyUser(),
                HttpStatus.OK
        );
    }

}
