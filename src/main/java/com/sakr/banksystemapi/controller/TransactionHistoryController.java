package com.sakr.banksystemapi.controller;

import com.sakr.banksystemapi.model.TransactionHistoryModel;
import com.sakr.banksystemapi.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class TransactionHistoryController {

    private final TransactionService transactionService;

    @GetMapping("/{cardId}")
    public List<TransactionHistoryModel> getCardHistory(
            @PathVariable int cardId) {
        return transactionService.transactionHistory(cardId);
    }
}
