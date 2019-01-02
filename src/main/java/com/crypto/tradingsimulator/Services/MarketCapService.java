package com.crypto.tradingsimulator.Services;

import com.crypto.tradingsimulator.Repositories.CoinsRepository;
import com.crypto.tradingsimulator.models.currencies.Coin;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MarketCapService {

    private final CoinsRepository coinsRepository;

    @Autowired
    public MarketCapService(CoinsRepository coinsRepository) {
        this.coinsRepository = coinsRepository;
    }

    public List<Coin> getAllCoins() {
        return coinsRepository.findAll();
    }
}
