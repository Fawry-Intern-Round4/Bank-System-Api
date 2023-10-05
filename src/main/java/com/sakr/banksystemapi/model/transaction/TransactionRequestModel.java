package com.sakr.banksystemapi.model.transaction;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestModel {

    @NotEmpty(message = "The Card Number is required.")
    @Size(min = 16, max = 16, message = "the Card Numbers Must be Exactly 16 Numbers")
    @Pattern(regexp = "^[0-9]+$",message = "Card Numbers Can't Contain Chars")
    private String cardNumber;

    @NotEmpty(message = "The CVV is required.")
    @Size(min = 3, max = 3, message = "the CVV Must be Exactly 3 Numbers")
    @Pattern(regexp = "^[0-9]+$",message = "CVV Can't Contain Chars")
    private String cvv;

    @NotNull(message = "The Money Amount is required.")
    @Positive(message = "The Money Amount must be greater than 0")
    private BigDecimal amount;
}
