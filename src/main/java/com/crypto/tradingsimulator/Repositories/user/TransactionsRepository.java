package com.crypto.tradingsimulator.Repositories.user;

import com.crypto.tradingsimulator.models.trading.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TransactionsRepository extends JpaRepository<Transaction, Long> {

}