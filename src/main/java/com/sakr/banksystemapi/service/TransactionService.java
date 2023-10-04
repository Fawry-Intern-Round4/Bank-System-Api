package com.sakr.banksystemapi.service;


import com.sakr.banksystemapi.model.transaction.TransactionRequestModel;

import java.util.List;

public interface TransactionService {
    void deposit(TransactionRequestModel request);

    void withdraw(TransactionRequestModel request);
}
