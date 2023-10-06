package com.sakr.banksystemapi.service;

import com.sakr.banksystemapi.entity.User;
import com.sakr.banksystemapi.model.user.UserResponseModel;


public interface UserService {
    User findUserByEmail(String email);

    boolean isUserEmailOrPhoneExist(String email, String phone);

    User saveUser(User user);

    UserResponseModel getMyProfileInfo();

}
