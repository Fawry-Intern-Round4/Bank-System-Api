package com.sakr.banksystemapi.repository;

import com.sakr.banksystemapi.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Boolean existsByCardNumber(String cardNumber);

     Optional<Account> findByCardNumber(String cardNumber);

     Boolean existsByCardNumberAndCvv(String cardNumber, String cvv);
}
