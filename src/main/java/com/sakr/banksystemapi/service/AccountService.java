package com.sakr.banksystemapi.service;

import com.sakr.banksystemapi.model.AccountResponseModel;

import java.util.List;

public interface AccountService {
    AccountResponseModel createAccount();

    List<AccountResponseModel> getUserAccounts();
}
