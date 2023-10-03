package com.sakr.banksystemapi.service;

import com.sakr.banksystemapi.entity.Account;
import com.sakr.banksystemapi.entity.User;

public interface AccountGeneratorService {

    Account generateNewAccount(User user);

}
