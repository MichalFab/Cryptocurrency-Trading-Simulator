package com.crypto.tradingsimulator.models.trading;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "user_coins")
public class UserCoins {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
}
