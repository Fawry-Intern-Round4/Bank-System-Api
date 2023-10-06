package com.sakr.banksystemapi.mapper;

import com.sakr.banksystemapi.entity.Transaction;
import com.sakr.banksystemapi.model.account.AccountTransactionHistoryModel;

public interface TransactionHistoryMapper {

    AccountTransactionHistoryModel toResponse(Transaction transaction);

}
