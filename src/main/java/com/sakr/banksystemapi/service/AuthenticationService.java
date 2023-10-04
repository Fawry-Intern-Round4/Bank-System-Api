package com.sakr.banksystemapi.service;

import com.sakr.banksystemapi.model.auth.AuthenticationRequestModel;
import com.sakr.banksystemapi.model.auth.AuthenticationResponseModel;
import com.sakr.banksystemapi.model.auth.RegisterRequestModel;

public interface AuthenticationService {

    AuthenticationResponseModel register(RegisterRequestModel request);

    AuthenticationResponseModel authenticate(AuthenticationRequestModel request);
}
