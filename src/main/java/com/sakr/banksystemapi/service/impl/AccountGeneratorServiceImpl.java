package com.sakr.banksystemapi.service.impl;

import com.sakr.banksystemapi.entity.Account;
import com.sakr.banksystemapi.entity.User;
import com.sakr.banksystemapi.repository.AccountRepository;
import com.sakr.banksystemapi.service.AccountGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountGeneratorServiceImpl implements AccountGeneratorService {

    private final static Random random = new Random();
    private final AccountRepository accountRepository;

    @Override
    public Account generateNewAccount(User user) {
        return Account.builder()
                .cardNumber(uniqueCardNumber())
                .cvv(cvv())
                .balance(BigDecimal.valueOf(0.0))
                .status(true)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .user(user)
                .build();
    }

    private String uniqueCardNumber() {
        String cardNumber;

        do {
            cardNumber = generateNumber(16);
        } while (accountRepository.existsByCardNumber(cardNumber));

        return cardNumber;
    }

    private String cvv() {
        return generateNumber(3);
    }


    private String generateNumber(int len) {
        StringBuilder number = new StringBuilder();

        for (int i = 0; i < len; i++) {
            number.append(random.nextInt(10));
        }

        return number.toString();
    }

}
