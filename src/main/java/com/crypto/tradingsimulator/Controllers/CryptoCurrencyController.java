package com.crypto.tradingsimulator.Controllers;


import com.crypto.tradingsimulator.Exceptions.BadRequestException;
import com.crypto.tradingsimulator.Repositories.CoinsRepository;
import com.crypto.tradingsimulator.models.currencies.Coin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/coins")
public class CryptoCurrencyController {

    private final CoinsRepository coinsRepository;

    @Autowired
    public CryptoCurrencyController(CoinsRepository coinsRepository) {
        this.coinsRepository = coinsRepository;
    }

    @GetMapping(value = "/all/")
    public List<Coin> allCoinsData() {
        for(Coin c : coinsRepository.findAll()) {
            System.out.println("test: " + c.getName());
        }
       return coinsRepository.findAll();
    }

    @PostMapping(value = "/{coinId}/")
    public Coin coinData(@PathVariable("coinId") String coinId) {
        return coinsRepository.findById(coinId).orElseThrow(() -> new BadRequestException("Coin not found"));
    }


}
