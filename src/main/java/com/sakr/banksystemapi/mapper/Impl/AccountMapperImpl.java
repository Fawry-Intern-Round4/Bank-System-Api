package com.sakr.banksystemapi.mapper.Impl;

import com.sakr.banksystemapi.entity.Account;
import com.sakr.banksystemapi.mapper.AccountMapper;
import com.sakr.banksystemapi.model.AccountResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountMapperImpl implements AccountMapper {
    @Override
    public AccountResponseModel toResponse(Account account) {
        return AccountResponseModel.builder()
                .cardNumber(account.getCardNumber())
                .cvv(account.getCvv())
                .balance(account.getBalance())
                .status(account.getStatus())
                .createdAt(account.getCreatedAt())
                .build();
    }
}
