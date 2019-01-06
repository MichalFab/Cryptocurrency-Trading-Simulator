package com.crypto.tradingsimulator.models.trading;

import com.crypto.tradingsimulator.models.user.User;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Builder
@Table(name = "balance")
public class UserBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "userBalance")
    private User user;

    BigDecimal balance;
}
