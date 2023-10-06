package com.sakr.banksystemapi.service.impl;

import com.sakr.banksystemapi.entity.Account;
import com.sakr.banksystemapi.entity.Transaction;
import com.sakr.banksystemapi.entity.enumtypes.TransactionType;
import com.sakr.banksystemapi.exceptions.customexceptions.NotAuthToSeeResourceException;
import com.sakr.banksystemapi.model.transaction.TransactionRequestModel;
import com.sakr.banksystemapi.repository.TransactionRepository;
import com.sakr.banksystemapi.service.AccountService;
import com.sakr.banksystemapi.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final AccountService accountService;
    private final TransactionRepository transactionRepository;
    @Override
    public void deposit(TransactionRequestModel request) {
        Account account = validateCard(request);

        performDeposit(account, request.getAmount());
    }

    @Override
    public void withdraw(TransactionRequestModel request) {
        Account account = validateCard(request);

       performWithdraw(account, request.getAmount());
    }

    private void performDeposit(Account account, BigDecimal amount){
        account.setBalance(account.getBalance().add(amount));
        String transactionNote = "Add " + amount + " to " + account.getUser().getEmail();

        saveTransaction(account, amount, transactionNote);
    }

    private void performWithdraw(Account account, BigDecimal amount){
        if(account.getBalance().compareTo(amount) < 0){
            throw new IllegalArgumentException("You Don't Have Enough Money In Your Card");
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


    private Account validateCard(TransactionRequestModel request){
        boolean exist = accountService.isAccountExist(request.getCardNumber(), request.getCvv());

        if(!exist){
            throw new IllegalArgumentException("That Card Is Not Valid!");
        }

        Account account = accountService.findAccountByCardNumber(request.getCardNumber());

        if(!account.getStatus() || !account.getUser().getStatus()){
            throw new BadCredentialsException("Your Account Is Disabled");
        }

        return account;
    }

}
