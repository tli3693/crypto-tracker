package com.troy.crypto.tracker.repository;

import com.troy.crypto.tracker.domain.UserCoin;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCoinRepository extends MongoRepository<UserCoin, String> {
    List<UserCoin> findByUsername(String username);
    List<UserCoin> findByUsernameAndCoinSymbol(String username, String coinSymbol);

    UserCoin findByTradeIdAndUsername(String tradeId, String username);
}
