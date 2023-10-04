package com.sakr.banksystemapi.mapper;

import com.sakr.banksystemapi.entity.User;
import com.sakr.banksystemapi.model.auth.AuthenticationResponseModel;
import com.sakr.banksystemapi.model.auth.RegisterRequestModel;

public interface AuthenticationMapper {
    User toEntity(RegisterRequestModel requestModel);

    AuthenticationResponseModel toAuthResponse(String token);
}
