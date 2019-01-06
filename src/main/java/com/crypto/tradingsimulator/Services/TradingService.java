package com.crypto.tradingsimulator.Services;

import com.crypto.tradingsimulator.Exceptions.AppException;
import com.crypto.tradingsimulator.Exceptions.BadRequestException;
import com.crypto.tradingsimulator.Repositories.CoinsRepository;
import com.crypto.tradingsimulator.Repositories.user.UserRepository;
import com.crypto.tradingsimulator.Security.UserPrincipal;

import com.crypto.tradingsimulator.models.trading.UserBalance;
import com.crypto.tradingsimulator.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TradingService {

    private UserRepository userRepository;

    private CoinsRepository coinsRepository;

    @Autowired
    public TradingService(UserRepository userRepository, CoinsRepository coinsRepository) {
        this.userRepository = userRepository;
        this.coinsRepository = coinsRepository;
    }


    public void buyCoin(String coinId, BigDecimal amount, UserPrincipal currentUser) {
        User user = findUser(currentUser);
        BigDecimal coinPrice = calculateCoinPrice(coinId);
        try {
            UserBalance userBalance = user.getUserBalance();
            BigDecimal newBalanceValue = calculateNewBalance(userBalance.getBalance(), coinPrice, amount);
            UserBalance newUserBalance = UserBalance.builder().balance(newBalanceValue).user(user).build();
            user.setUserBalance(newUserBalance);
        } catch (Exception e) {
            throw new AppException("Cannot finish operation");
        }
        userRepository.save(user);
    }


    public void sellCoin(String coinId, BigDecimal amount, UserPrincipal currentUser) {
        User user = findUser(currentUser);
        BigDecimal coinPrice = calculateCoinPrice(coinId);
    }

    private BigDecimal calculateNewBalance(BigDecimal balance, BigDecimal coinPrice, BigDecimal amount) {
        BigDecimal coinTotalCost = coinPrice.multiply(amount);
        if (isBalanceSufficient(balance, coinTotalCost)) {
            return balance.subtract(coinTotalCost);
        } else {
            throw new AppException("Insufficient funds to carry out the operation!");
        }
    }

    private User findUser(UserPrincipal currentUser) {
        return userRepository.findByUsernameOrEmail(
                currentUser.getUsername(),
                currentUser.getEmail())
                .orElseThrow(() -> new BadRequestException("User not found"));
    }

    private BigDecimal calculateCoinPrice(String coinId) {
        return new BigDecimal(coinsRepository.findById(coinId).orElseThrow(() -> new AppException("Coin not found")).getPrice());
    }

    private boolean isBalanceSufficient(BigDecimal balance, BigDecimal coinTotalCost) {
        return balance.compareTo(coinTotalCost) >= 0;
    }


}
