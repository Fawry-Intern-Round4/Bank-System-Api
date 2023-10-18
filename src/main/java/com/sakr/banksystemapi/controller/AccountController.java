package com.sakr.banksystemapi.controller;

import com.sakr.banksystemapi.model.ResponseModel;
import com.sakr.banksystemapi.model.account.DeactivateResponseModel;
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
    public ResponseEntity<ResponseModel> getUserAccounts() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseModel
                        .builder()
                        .success(true)
                        .payload(accountService.getUserAccounts())
                        .build()
                );
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<ResponseModel> getAccountHistory(
            @Valid @PathVariable int accountId
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseModel
                        .builder()
                        .success(true)
                        .payload(accountService.accountTransactionHistory(accountId))
                        .build()
                );
    }

    @PostMapping
    public ResponseEntity<ResponseModel> createAccount() {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseModel
                        .builder()
                        .success(true)
                        .payload(accountService.createAccount())
                        .build()
                );

    }

    @PostMapping(value = "/{accountId}")
    public ResponseEntity<ResponseModel> activateMyAccount(
            @Valid @PathVariable int accountId
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseModel
                        .builder()
                        .success(true)
                        .payload(accountService.activateMyAccount(accountId))
                        .build()
                );
    }

    @DeleteMapping(value = "/{accountId}")
    public ResponseEntity<ResponseModel> deactivateMyAccount(
            @Valid @PathVariable int accountId
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseModel
                        .builder()
                        .success(true)
                        .payload(accountService.deactivateMyAccount(accountId))
                        .build()
                );
    }

}

