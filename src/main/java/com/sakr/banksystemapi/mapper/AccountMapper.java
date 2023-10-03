package com.sakr.banksystemapi.mapper;

import com.sakr.banksystemapi.entity.Account;
import com.sakr.banksystemapi.model.AccountResponseModel;

public interface AccountMapper {

    AccountResponseModel toResponse(Account account);

}
