package com.sakr.banksystemapi.mapper.Impl;

import com.sakr.banksystemapi.mapper.ErrorMapper;
import com.sakr.banksystemapi.model.error.ResponseErrorModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class ErrorMapperImpl implements ErrorMapper {
    @Override
    public ResponseErrorModel toErrorResponse(HttpStatus status, String message) {
        return ResponseErrorModel
                .builder()
                .status(status.value())
                .message(message)
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}
