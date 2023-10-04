package com.sakr.banksystemapi.controller;

import com.sakr.banksystemapi.model.user.UserResponseModel;
import com.sakr.banksystemapi.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;


    @GetMapping()
    public UserResponseModel getUserInfo() {
        return userInfoService.getMyProfileInfo();
    }

}
