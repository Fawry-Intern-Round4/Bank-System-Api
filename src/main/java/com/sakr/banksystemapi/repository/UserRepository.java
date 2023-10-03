package com.sakr.banksystemapi.repository;

import com.sakr.banksystemapi.entity.Account;
import com.sakr.banksystemapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);

    @Query("FROM Account where user.id = :theId")
    List<Account> getAllUserAccountsByUserId(@Param("theId") int theId);
}
