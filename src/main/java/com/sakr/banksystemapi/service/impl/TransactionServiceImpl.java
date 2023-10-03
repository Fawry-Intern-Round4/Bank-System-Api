package com.sakr.banksystemapi.service.impl;

import com.sakr.banksystemapi.entity.Account;
import com.sakr.banksystemapi.entity.Transaction;
import com.sakr.banksystemapi.entity.enumtypes.TransactionType;
import com.sakr.banksystemapi.model.TransactionRequestModel;
import com.sakr.banksystemapi.repository.AccountRepository;
import com.sakr.banksystemapi.repository.TransactionRepository;
import com.sakr.banksystemapi.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    @Override
    public void deposit(TransactionRequestModel request) {
        validateCard(request);

        Account account = accountRepository.findByCardNumber(request.getCardNumber())
                .orElseThrow(()-> new UsernameNotFoundException("there is no such account"));

        account.setBalance(account.getBalance().add(request.getAmount()));

        Transaction transaction =
                Transaction.builder()
                        .transactionType(TransactionType.DEPOSIT)
                        .amount(request.getAmount())
                        .note("Add " + request.getAmount() + " to " + account.getUser().getEmail())
                        .createdAt(new Timestamp(System.currentTimeMillis()))
                        .account(account)
                        .build();

        transactionRepository.save(transaction);
    }

    @Override
    public void withdraw(TransactionRequestModel request) {
        validateCard(request);

        Account account = accountRepository.findByCardNumber(request.getCardNumber())
                .orElseThrow(()-> new UsernameNotFoundException("there is no such account"));

        account.setBalance(account.getBalance().subtract(request.getAmount()));

        Transaction transaction =
                Transaction.builder()
                        .transactionType(TransactionType.WITHDRAW)
                        .amount(request.getAmount())
                        .note("subtract " + request.getAmount() + " from " + account.getUser().getEmail())
                        .createdAt(new Timestamp(System.currentTimeMillis()))
                        .account(account)
                        .build();

        transactionRepository.save(transaction);

    }

    private void validateCard(TransactionRequestModel request){
        boolean valid =
                accountRepository.existsByCardNumberAndCvv(request.getCardNumber(), request.getCvv());

        System.out.println(valid);
        if(!valid){
            throw new UsernameNotFoundException("it's not valid card");
        }
    }

}
