package com.sakr.banksystemapi.mapper;

import com.sakr.banksystemapi.entity.User;
import com.sakr.banksystemapi.model.AuthenticationResponseModel;
import com.sakr.banksystemapi.model.RegisterRequestModel;

public interface UserMapper {
    User toEntity(RegisterRequestModel requestModel);

    AuthenticationResponseModel toAuthResponse(String token);
}
