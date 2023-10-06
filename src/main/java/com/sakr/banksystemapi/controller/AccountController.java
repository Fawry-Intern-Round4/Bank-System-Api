package com.sakr.banksystemapi.controller;

import com.sakr.banksystemapi.model.account.AccountResponseModel;
import com.sakr.banksystemapi.model.account.AccountTransactionHistoryModel;
import com.sakr.banksystemapi.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponseModel> createAccount() {
        return new ResponseEntity<>(
                accountService.createAccount(),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<AccountResponseModel>> getUserAccounts() {
        return new ResponseEntity<>(
                accountService.getUserAccounts(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{AccountId}")
    public ResponseEntity<List<AccountTransactionHistoryModel>> getAccountHistory(
            @Valid @PathVariable int AccountId
    ) {
        return new ResponseEntity<>(
                accountService.accountTransactionHistory(AccountId),
                HttpStatus.OK
        );
    }
}

