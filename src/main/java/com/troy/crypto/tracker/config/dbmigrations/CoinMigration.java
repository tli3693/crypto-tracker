package com.troy.crypto.tracker.config.dbmigrations;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.troy.crypto.tracker.config.Constants;
import com.troy.crypto.tracker.domain.Authority;
import com.troy.crypto.tracker.domain.Coin;
import com.troy.crypto.tracker.domain.User;
import com.troy.crypto.tracker.security.AuthoritiesConstants;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * Creates the coin stuff
 */
@ChangeLog(order = "002")
public class CoinMigration {

    @ChangeSet(order = "01", author = "troy", id = "01-addCoins")
    public void addCoins(MongockTemplate mongoTemplate) {
        Coin coin = new Coin("Bitcoin", "BTC", new BigDecimal("60000"));
        mongoTemplate.save(coin);
    }
    //    @ChangeSet(order = "01", author = "troy", id = "02-addUserCoins")
    //    public void addUserCoins(MongockTemplate mongoTemplate) {
    //        mongoTemplate.save(new UserCoin("BTC", new BigDecimal("2921.09"), new BigDecimal("6"), new Date(), "troy", ActionType.BUY));
    //        mongoTemplate.save(new UserCoin("BTC", new BigDecimal("2231.22"), new BigDecimal("2"), new Date(), "troy", ActionType.BUY));
    //        mongoTemplate.save(new UserCoin("LTC", new BigDecimal("241.99"), new BigDecimal("1"), new Date(), "troy", ActionType.BUY));
    //        mongoTemplate.save(new UserCoin("ETH", new BigDecimal("2597.05"), new BigDecimal("3"), new Date(), "troy", ActionType.BUY));
    //        mongoTemplate.save(new UserCoin("BTC", new BigDecimal("55143.20"), new BigDecimal("4.25"), new Date(), "troy", ActionType.BUY));
    //    }
}
