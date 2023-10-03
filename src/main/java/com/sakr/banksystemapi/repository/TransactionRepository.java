package com.sakr.banksystemapi.repository;

import com.sakr.banksystemapi.entity.Account;
import com.sakr.banksystemapi.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findByAccount(Account account);
}
