package com.sakr.banksystemapi.mapper;

import com.sakr.banksystemapi.entity.Transaction;
import com.sakr.banksystemapi.model.TransactionHistoryModel;

public interface TransactionHistoryMapper {

    TransactionHistoryModel toResponse(Transaction transaction);

}
