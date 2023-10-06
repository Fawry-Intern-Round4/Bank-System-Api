package com.sakr.banksystemapi.model.account;

import com.sakr.banksystemapi.entity.enumtypes.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountTransactionHistoryModel {
    private int id;
    private TransactionType transactionType;
    private BigDecimal amount;
    private String note;
    private Timestamp createdAt;
}
