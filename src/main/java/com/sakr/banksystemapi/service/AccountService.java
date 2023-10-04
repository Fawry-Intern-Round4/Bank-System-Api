package com.sakr.banksystemapi.service;

import com.sakr.banksystemapi.model.account.AccountResponseModel;
import com.sakr.banksystemapi.model.transaction.TransactionHistoryModel;

import java.util.List;

public interface AccountService {
    AccountResponseModel createAccount();

    List<AccountResponseModel> getUserAccounts();

    List<TransactionHistoryModel> transactionHistory(int cardId);
}
