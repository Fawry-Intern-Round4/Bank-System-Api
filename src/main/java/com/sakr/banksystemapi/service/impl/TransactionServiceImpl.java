package com.sakr.banksystemapi.service.impl;

import com.sakr.banksystemapi.entity.Account;
import com.sakr.banksystemapi.entity.Transaction;
import com.sakr.banksystemapi.entity.enumtypes.TransactionType;
import com.sakr.banksystemapi.exceptions.customexceptions.ResourceNotFoundException;
import com.sakr.banksystemapi.model.transaction.TransactionRequestModel;
import com.sakr.banksystemapi.repository.AccountRepository;
import com.sakr.banksystemapi.repository.TransactionRepository;
import com.sakr.banksystemapi.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
                .orElseThrow(()-> new ResourceNotFoundException("there is no such account"));

        performDeposit(account, request.getAmount());
    }

    @Override
    public void withdraw(TransactionRequestModel request) {

        validateCard(request);

        Account account = accountRepository.findByCardNumber(request.getCardNumber())
                .orElseThrow(()-> new ResourceNotFoundException("there is no such account"));

       performWithdraw(account, request.getAmount());
    }

    private void performDeposit(Account account, BigDecimal amount){
        account.setBalance(account.getBalance().add(amount));

        String transactionNote = "Add " + amount + " to " + account.getUser().getEmail();

        saveTransaction(account, amount, transactionNote);
    }

    private void performWithdraw(Account account, BigDecimal amount){
        if(account.getBalance().compareTo(amount) < 0){
            throw new ResourceNotFoundException("You Don't Have Enough Money In Your Card");
        }
        account.setBalance(account.getBalance().subtract(amount));

        String transactionNote = "subtract " + amount + " from " + account.getUser().getEmail();

        saveTransaction(account, amount, transactionNote);
    }


    private void saveTransaction(Account account,BigDecimal amount, String transactionNote){
        Transaction transaction =
                Transaction.builder()
                        .transactionType(TransactionType.WITHDRAW)
                        .amount(amount)
                        .note(transactionNote)
                        .createdAt(new Timestamp(System.currentTimeMillis()))
                        .account(account)
                        .build();

        transactionRepository.save(transaction);
    }


    private void validateCard(TransactionRequestModel request){
        boolean valid =
                accountRepository.existsByCardNumberAndCvv(request.getCardNumber(), request.getCvv());

        if(!valid){
            throw new ResourceNotFoundException("it's not valid card");
        }
    }

}
