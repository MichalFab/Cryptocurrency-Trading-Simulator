package com.crypto.tradingsimulator.Repositories;

import com.crypto.tradingsimulator.models.currencies.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoinsRepository extends JpaRepository<Coin, String> {

    @Override
    List<Coin> findAll();
}
