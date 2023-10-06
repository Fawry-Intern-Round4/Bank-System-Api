package com.sakr.banksystemapi.service.impl;

import com.sakr.banksystemapi.entity.Account;
import com.sakr.banksystemapi.entity.User;
import com.sakr.banksystemapi.exceptions.customexceptions.NotAuthToSeeResourceException;
import com.sakr.banksystemapi.mapper.AccountMapper;
import com.sakr.banksystemapi.mapper.TransactionHistoryMapper;
import com.sakr.banksystemapi.model.account.AccountResponseModel;
import com.sakr.banksystemapi.model.account.AccountTransactionHistoryModel;
import com.sakr.banksystemapi.repository.AccountRepository;
import com.sakr.banksystemapi.repository.TransactionRepository;
import com.sakr.banksystemapi.repository.UserRepository;
import com.sakr.banksystemapi.service.AccountGeneratorService;
import com.sakr.banksystemapi.service.AccountService;
import com.sakr.banksystemapi.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final AccountGeneratorService accountGeneratorService;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final AccountMapper accountMapper;
    private final TransactionHistoryMapper transactionHistoryMapper;

    @Override
    public AccountResponseModel createAccount() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByEmail(email);

        Account account = accountGeneratorService.generateNewAccount(user);

        return accountMapper
                .toResponse(accountRepository.save(account));
    }

    @Override
    public List<AccountResponseModel> getUserAccounts() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByEmail(email);

        return userRepository.getAllUserAccountsByUserId(user.getId())
                .stream()
                .map(accountMapper::toResponse)
                .toList();
    }

    @Override
    public List<AccountTransactionHistoryModel> accountTransactionHistory(int cardId) {
        Account account = findAccountById(cardId);

        validateAuthToSeeTransactionHistory(account);

        return transactionRepository.findByAccount(account)
                .stream()
                .map(transactionHistoryMapper::toResponse)
                .toList();
    }

    @Override
    public Account findAccountByCardNumber(String cardNumber) {
        return accountRepository.findByCardNumber(cardNumber)
                .orElseThrow(()-> new IllegalArgumentException("Account Not Found!!"));

    }

    @Override
    public Account findAccountById(int cardId) {
        return accountRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Account Not Found!"));

    }

    @Override
    public boolean isValidAccount(String cardNumber, String cvv) {
        return accountRepository
                .existsByCardNumberAndCvv(cardNumber, cvv);
    }

    private void validateAuthToSeeTransactionHistory(Account account) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userService.findUserByEmail(email);

        if (!account.getUser().equals(user))
            throw new NotAuthToSeeResourceException("Not Auth To See Transaction History");
    }

}
