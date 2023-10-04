package com.sakr.banksystemapi.service.impl;

import com.sakr.banksystemapi.entity.Account;
import com.sakr.banksystemapi.entity.Transaction;
import com.sakr.banksystemapi.entity.User;
import com.sakr.banksystemapi.exceptions.customexceptions.ResourceNotFoundException;
import com.sakr.banksystemapi.mapper.AccountMapper;
import com.sakr.banksystemapi.mapper.TransactionHistoryMapper;
import com.sakr.banksystemapi.model.account.AccountResponseModel;
import com.sakr.banksystemapi.model.transaction.TransactionHistoryModel;
import com.sakr.banksystemapi.repository.AccountRepository;
import com.sakr.banksystemapi.repository.TransactionRepository;
import com.sakr.banksystemapi.repository.UserRepository;
import com.sakr.banksystemapi.service.AccountGeneratorService;
import com.sakr.banksystemapi.service.AccountService;
import com.sakr.banksystemapi.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final UserInfoService userService;
    private final AccountGeneratorService accountGeneratorService;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final AccountMapper accountMapper;
    private final TransactionHistoryMapper transactionHistoryMapper;

    @Override
    public AccountResponseModel createAccount() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userService.findUserByEmail(email);

        Account account= accountGeneratorService.generateNewAccount(user);

        return accountMapper.toResponse(account);
    }

    @Override
    public List<AccountResponseModel> getUserAccounts() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userService.findUserByEmail(email);

        List<Account> accounts= userRepository.getAllUserAccountsByUserId(user.getId());

        return accounts.stream().map(accountMapper::toResponse).toList();
    }

    @Override
    public List<TransactionHistoryModel> transactionHistory(int cardId) {
        Account account = accountRepository.findById(cardId)
                .orElseThrow(() -> new ResourceNotFoundException("there is no such account"));

        validateAuthToSeeTransactionHistory(account);

        List<Transaction> transactions = transactionRepository.findByAccount(account);

        return transactions.stream().map(transactionHistoryMapper::toResponse).toList();
    }

    private void validateAuthToSeeTransactionHistory(Account account){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userService.findUserByEmail(email);


        if(!account.getUser().equals(user))
            throw new ResourceNotFoundException("You Are not Auth to See that transaction History");
    }

}
