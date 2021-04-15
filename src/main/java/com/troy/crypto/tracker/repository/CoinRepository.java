package com.troy.crypto.tracker.repository;

import com.troy.crypto.tracker.domain.Coin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinRepository extends MongoRepository<Coin, String> {
    Coin findBySymbol(String symbol);
}
