package com.sakr.banksystemapi.controller;

import com.sakr.banksystemapi.model.TransactionHistoryModel;
import com.sakr.banksystemapi.model.TransactionRequestModel;
import com.sakr.banksystemapi.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class TransactionHistoryController {

    private final TransactionService transactionService;

    @GetMapping("/{cardNumber}")
    public List<TransactionHistoryModel> getCardHistory(
            @PathVariable String cardNumber) {
        return transactionService.transactionHistory(cardNumber);
    }
}
