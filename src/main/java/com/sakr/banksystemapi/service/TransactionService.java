package com.sakr.banksystemapi.service;


import com.sakr.banksystemapi.model.TransactionRequestModel;

public interface TransactionService {
    void deposit(TransactionRequestModel request);

    void withdraw(TransactionRequestModel request);
}
