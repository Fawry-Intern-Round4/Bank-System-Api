package com.sakr.banksystemapi.service;

import com.sakr.banksystemapi.entity.User;
import com.sakr.banksystemapi.model.AuthenticationRequestModel;
import com.sakr.banksystemapi.model.AuthenticationResponseModel;
import com.sakr.banksystemapi.model.RegisterRequestModel;

public interface UserService {

    User findUserByEmail(String email);

    AuthenticationResponseModel register(RegisterRequestModel request);

    AuthenticationResponseModel authenticate(AuthenticationRequestModel request);
}
