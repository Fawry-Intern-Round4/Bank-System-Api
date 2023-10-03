package com.sakr.banksystemapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseErrorModel {

    private int status;
    private String message;
    private Timestamp occuredAt;
}
