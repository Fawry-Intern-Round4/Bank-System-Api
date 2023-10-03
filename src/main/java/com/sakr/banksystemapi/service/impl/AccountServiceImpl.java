package com.sakr.banksystemapi.service.impl;

import com.sakr.banksystemapi.entity.Account;
import com.sakr.banksystemapi.entity.User;
import com.sakr.banksystemapi.mapper.AccountMapper;
import com.sakr.banksystemapi.model.AccountResponseModel;
import com.sakr.banksystemapi.repository.AccountRepository;
import com.sakr.banksystemapi.security.JwtService;
import com.sakr.banksystemapi.service.AccountGeneratorService;
import com.sakr.banksystemapi.service.AccountService;
import com.sakr.banksystemapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserService userService;
    private final AccountGeneratorService accountGeneratorService;
    private final AccountMapper accountMapper;

    @Override
    public AccountResponseModel createAccount() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userService.findUserByEmail(email);

        Account account= accountGeneratorService.generateNewAccount(user);

        return accountMapper.toResponse(account);
    }

}
