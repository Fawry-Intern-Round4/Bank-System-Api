package com.sakr.banksystemapi.mapper;

import com.sakr.banksystemapi.model.account.DeactivateResponseModel;

public interface DeactivateAccountMapper {

    DeactivateResponseModel toResponse(String message);
}
