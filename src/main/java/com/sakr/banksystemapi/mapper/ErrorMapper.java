package com.sakr.banksystemapi.mapper;

import com.sakr.banksystemapi.model.error.ResponseErrorModel;
import org.springframework.http.HttpStatus;

public interface ErrorMapper {
    ResponseErrorModel toErrorResponse(HttpStatus status, String message);
}
