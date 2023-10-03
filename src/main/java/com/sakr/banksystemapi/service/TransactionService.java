package com.sakr.banksystemapi.service;


import com.sakr.banksystemapi.model.TransactionHistoryModel;
import com.sakr.banksystemapi.model.TransactionRequestModel;

import java.util.List;

public interface TransactionService {
    void deposit(TransactionRequestModel request);

    void withdraw(TransactionRequestModel request);

    List<TransactionHistoryModel> transactionHistory(String cardNumber);
}
