package com.crypto.tradingsimulator.Controllers;

import com.crypto.tradingsimulator.Security.UserPrincipal;
import com.crypto.tradingsimulator.Services.TradingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController("/trade")
public class TradeController {

    @Autowired
    private TradingService tradingService;

    @GetMapping(value = "/buy/{coin}/{amount}")
    @PreAuthorize("hasRole('USER')")
    public void buyCoin(@PathVariable("coin") String coinId, @PathVariable("amount") BigDecimal amount, @AuthenticationPrincipal UserPrincipal currentUser) {
        tradingService.buyCoin(coinId, amount, currentUser);
    }

    @GetMapping(value = "/sell/{coin}/{amount}")
    public void sellCoin(@PathVariable("coin") String coinId, @PathVariable("amount") BigDecimal amount, @AuthenticationPrincipal UserPrincipal currentUser) {
    }

}
