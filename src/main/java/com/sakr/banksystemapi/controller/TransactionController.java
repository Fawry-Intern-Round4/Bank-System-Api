package com.sakr.banksystemapi.controller;

import com.sakr.banksystemapi.model.ResponseModel;
import com.sakr.banksystemapi.model.transaction.TransactionRequestModel;
import com.sakr.banksystemapi.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/deposit")
    public ResponseEntity<ResponseModel> deposit(
            @Valid @RequestBody TransactionRequestModel request
    ) {
        transactionService.deposit(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseModel
                        .builder()
                        .success(true)
                        .build()
                );
    }

    @PostMapping("/withdraw")
    public ResponseEntity<ResponseModel> withdraw(
            @Valid @RequestBody TransactionRequestModel request
    ) {
        transactionService.withdraw(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseModel
                        .builder()
                        .success(true)
                        .build()
                );
    }

}
