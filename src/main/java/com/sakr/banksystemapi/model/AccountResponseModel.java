package com.sakr.banksystemapi.model;

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
public class AccountResponseModel {
    private int id;
    private String cardNumber;
    private String cvv;
    private BigDecimal balance;
    private Boolean status;
    private Timestamp createdAt;
}
