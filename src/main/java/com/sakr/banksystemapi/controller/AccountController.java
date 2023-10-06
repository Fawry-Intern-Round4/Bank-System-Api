package com.sakr.banksystemapi.controller;

import com.sakr.banksystemapi.model.DeactivateResponseModel;
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

    @GetMapping
    public ResponseEntity<List<AccountResponseModel>> getUserAccounts() {
        return new ResponseEntity<>(
                accountService.getUserAccounts(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<List<AccountTransactionHistoryModel>> getAccountHistory(
            @Valid @PathVariable int accountId
    ) {
        return new ResponseEntity<>(
                accountService.accountTransactionHistory(accountId),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<AccountResponseModel> createAccount() {
        return new ResponseEntity<>(
                accountService.createAccount(),
                HttpStatus.CREATED
        );
    }

    @PostMapping(value = "/{accountId}")
    public ResponseEntity<DeactivateResponseModel> activateMyAccount(
            @Valid @PathVariable int accountId
    ){
        return new ResponseEntity<>(
                accountService.activateMyAccount(accountId),
                HttpStatus.OK
        );
    }

    @DeleteMapping(value = "/{accountId}")
    public ResponseEntity<DeactivateResponseModel> deactivateMyAccount(
            @Valid @PathVariable int accountId
    ){
        return new ResponseEntity<>(
                accountService.deactivateMyAccount(accountId),
                HttpStatus.OK
        );
    }

}

