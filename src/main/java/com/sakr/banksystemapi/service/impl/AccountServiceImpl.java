package com.sakr.banksystemapi.service.impl;

import com.sakr.banksystemapi.entity.Account;
import com.sakr.banksystemapi.entity.User;
import com.sakr.banksystemapi.exceptions.customexceptions.NotAuthToSeeResourceException;
import com.sakr.banksystemapi.mapper.AccountMapper;
import com.sakr.banksystemapi.mapper.DeactivateAccountMapper;
import com.sakr.banksystemapi.mapper.TransactionHistoryMapper;
import com.sakr.banksystemapi.model.DeactivateResponseModel;
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
    private final DeactivateAccountMapper deactivateAccountMapper;

    @Override
    public AccountResponseModel createAccount() {
        User user = userService.getMyUser();

        Account account = accountGeneratorService.generateNewAccount(user);

        return accountMapper
                .toResponse(accountRepository.save(account));
    }

    @Override
    public List<AccountResponseModel> getUserAccounts() {
        User user = userService.getMyUser();

        return userRepository.getAllUserAccountsByUserId(user.getId())
                .stream()
                .map(accountMapper::toResponse)
                .toList();
    }

    @Override
    public DeactivateResponseModel deactivateMyAccount(int accountId) {
        Account account = findAccountById(accountId);
        validateAuthOnAccount(account);

        if (account.getStatus()) {
            account.setStatus(false);
            accountRepository.save(account);
        }
        return deactivateAccountMapper
                .toResponse("You Account Now Is Deactivated");
    }

    @Override
    public DeactivateResponseModel activateMyAccount(int accountId) {
        Account account = findAccountById(accountId);
        validateAuthOnAccount(account);

        if (!account.getStatus()) {
            account.setStatus(true);
            accountRepository.save(account);
        }

        return deactivateAccountMapper
                .toResponse("You Account Now Is Activated");
    }

    @Override
    public List<AccountTransactionHistoryModel> accountTransactionHistory(int accountId) {
        Account account = findAccountById(accountId);

        validateAuthOnAccount(account);

        return transactionRepository.findByAccount(account)
                .stream()
                .map(transactionHistoryMapper::toResponse)
                .toList();
    }

    @Override
    public Account findAccountByCardNumber(String cardNumber) {
        return accountRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account Not Found!!"));

    }

    @Override
    public Account findAccountById(int accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account Not Found!"));

    }

    @Override
    public boolean isAccountExist(String cardNumber, String cvv) {
        return accountRepository
                .existsByCardNumberAndCvv(cardNumber, cvv);
    }

    private void validateAuthOnAccount(Account account) {
        User user = userService.getMyUser();

        if (!account.getUser().equals(user))
            throw new NotAuthToSeeResourceException("Not Auth To See Transaction History");
    }

}
