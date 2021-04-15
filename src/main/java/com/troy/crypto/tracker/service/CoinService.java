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

    public CoinService(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
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
            coin = new Coin(cmcCoin.getName(), cmcCoin.getSymbol(), new BigDecimal(cmcCoin.getQuote().getUSD().getPrice()));
        }
        coin.setCost(new BigDecimal(cmcCoin.getQuote().getUSD().getPrice()));
        coin.setLastUpdated(new Date());
        coinRepository.save(coin);
    }
}
