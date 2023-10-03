package com.sakr.banksystemapi.repository;

import com.sakr.banksystemapi.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
