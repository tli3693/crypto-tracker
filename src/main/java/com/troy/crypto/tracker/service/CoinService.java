package com.troy.crypto.tracker.service;

import com.troy.crypto.tracker.domain.Coin;
import com.troy.crypto.tracker.repository.CoinRepository;
import com.troy.crypto.tracker.service.cmc.CMCCoin;
import com.troy.crypto.tracker.service.cmc.CMCResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CoinService {

    private final CoinRepository coinRepository;
    private final UserCoinMapper userCoinMapper;

    public CoinService(CoinRepository coinRepository, UserCoinMapper userCoinMapper) {
        this.coinRepository = coinRepository;
        this.userCoinMapper = userCoinMapper;
    }

    public void createTestCoins() {
        System.out.println("Creating coins");
        coinRepository.insert(new Coin("Ethereum", "ETH", new BigDecimal("2602.09")));
        coinRepository.insert(new Coin("Bitcoin", "BTC", new BigDecimal("12222.39")));
        coinRepository.insert(new Coin("Litecoin", "LTC", new BigDecimal("12222.39")));
    }

    public Coin findBySymbol(String symbol) {
        return coinRepository.findBySymbol(symbol);
    }

    public void deleteAll() {
        coinRepository.deleteAll();
    }

    public List<Coin> findAll() {
        return coinRepository.findAll();
    }

    public void refreshCoinPrices(CMCResponse commonListings) {
        saveOrUpdate(commonListings.getData().getBTC());
        saveOrUpdate(commonListings.getData().getETH());
        saveOrUpdate(commonListings.getData().getLTC());
        saveOrUpdate(commonListings.getData().getADA());
    }

    private void saveOrUpdate(CMCCoin cmcCoin) {
        Coin coin = coinRepository.findBySymbol(cmcCoin.getSymbol());
        if (coin == null) {
            coin = userCoinMapper.toCoin(cmcCoin);
        }
        coin.setCost(new BigDecimal(cmcCoin.getQuote().getUSD().getPrice()));
        coin.setPercent_change_24h(new BigDecimal(cmcCoin.getQuote().getUSD().getPercent_change_24h()));
        coin.setLastUpdated(cmcCoin.getLast_updated());
        coinRepository.save(coin);
    }
}
