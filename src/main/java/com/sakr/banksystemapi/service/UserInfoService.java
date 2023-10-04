package com.sakr.banksystemapi.service;

import com.sakr.banksystemapi.entity.User;
import com.sakr.banksystemapi.model.user.UserResponseModel;


public interface UserInfoService {
    User findUserByEmail(String email);

    UserResponseModel getMyProfileInfo();

}
