package com.sakr.banksystemapi.controller;

import com.sakr.banksystemapi.model.AccountResponseModel;
import com.sakr.banksystemapi.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public AccountResponseModel createAccount() {
        return accountService.createAccount();
    }

}
