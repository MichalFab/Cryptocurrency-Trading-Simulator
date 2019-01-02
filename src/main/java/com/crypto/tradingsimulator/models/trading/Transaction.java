package com.crypto.tradingsimulator.models.trading;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fromCoinId;

    private Long toCoinId;

    private BigDecimal amountFrom;

    private BigDecimal amountTo;

    private LocalDate date;

    private BigDecimal saldoBefore;

    private BigDecimal saldoAfter;
}
