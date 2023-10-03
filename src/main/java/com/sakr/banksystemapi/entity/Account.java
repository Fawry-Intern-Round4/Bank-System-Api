package com.sakr.banksystemapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "accounts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Column(name = "cvv", nullable = false)
    private String cvv;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;


    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,
    CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;
}
