package com.sakr.banksystemapi.mapper.Impl;

import com.sakr.banksystemapi.entity.Transaction;
import com.sakr.banksystemapi.mapper.TransactionHistoryMapper;
import com.sakr.banksystemapi.model.transaction.TransactionHistoryModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionHistoryMapperImpl implements TransactionHistoryMapper {
    @Override
    public TransactionHistoryModel toResponse(Transaction transaction) {
        return TransactionHistoryModel
                .builder()
                .id(transaction.getId())
                .transactionType(transaction.getTransactionType())
                .amount(transaction.getAmount())
                .note(transaction.getNote())
                .createdAt(transaction.getCreatedAt())
                .build();
    }
}
