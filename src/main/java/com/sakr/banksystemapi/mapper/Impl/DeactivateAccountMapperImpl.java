package com.sakr.banksystemapi.mapper.Impl;

import com.sakr.banksystemapi.mapper.DeactivateAccountMapper;
import com.sakr.banksystemapi.model.account.DeactivateResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class DeactivateAccountMapperImpl implements DeactivateAccountMapper {

    @Override
    public DeactivateResponseModel toResponse(String message) {
        return DeactivateResponseModel
                .builder()
                .message(message)
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}
