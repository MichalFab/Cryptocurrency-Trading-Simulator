package com.crypto.tradingsimulator.models.trading;

import com.crypto.tradingsimulator.models.user.User;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "balance")
public class UserBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "userBalance")
    private User user;

    BigDecimal balance;
}
