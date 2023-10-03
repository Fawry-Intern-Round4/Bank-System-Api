package com.sakr.banksystemapi.repository;

import com.sakr.banksystemapi.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
