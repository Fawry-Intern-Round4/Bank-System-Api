package com.sakr.banksystemapi.mapper;

import com.sakr.banksystemapi.entity.User;
import com.sakr.banksystemapi.model.user.UserResponseModel;

public interface UserMapper {

    UserResponseModel toResponse(User user);

}
