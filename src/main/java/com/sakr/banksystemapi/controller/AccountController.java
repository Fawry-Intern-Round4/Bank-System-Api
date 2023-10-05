package com.sakr.banksystemapi.controller;

import com.sakr.banksystemapi.model.account.AccountResponseModel;
import com.sakr.banksystemapi.model.transaction.TransactionHistoryModel;
import com.sakr.banksystemapi.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{AccountId}")
    public List<TransactionHistoryModel> getAccountHistory(
            @Valid @PathVariable int AccountId
    ) {
        return accountService.transactionHistory(AccountId);
    }
}

