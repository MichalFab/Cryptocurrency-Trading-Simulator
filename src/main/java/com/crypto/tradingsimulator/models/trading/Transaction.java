package com.crypto.tradingsimulator.models.trading;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String coinId;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private TransactionType transactionType;

    private LocalDate date;

    private BigDecimal balanceBefore;

    private BigDecimal balanceAfter;
}
