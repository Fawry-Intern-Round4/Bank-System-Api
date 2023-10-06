package com.sakr.banksystemapi.service;

import com.sakr.banksystemapi.entity.Account;
import com.sakr.banksystemapi.model.account.AccountResponseModel;
import com.sakr.banksystemapi.model.account.AccountTransactionHistoryModel;

import java.util.List;

public interface AccountService {
    AccountResponseModel createAccount();

    List<AccountResponseModel> getUserAccounts();

    List<AccountTransactionHistoryModel> accountTransactionHistory(int cardId);

    Account findAccountByCardNumber(String cardNumber);

    Account findAccountById(int cardId);

    boolean isValidAccount(String cardNumber, String cvv);
}
