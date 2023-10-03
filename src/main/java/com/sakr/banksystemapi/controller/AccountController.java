package com.sakr.banksystemapi.controller;

import com.sakr.banksystemapi.model.AccountResponseModel;
import com.sakr.banksystemapi.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public AccountResponseModel createAccount() {
        return accountService.createAccount();
    }

    @GetMapping
    public List<AccountResponseModel> getUserAccounts() {
        return accountService.getUserAccounts();
    }

}
