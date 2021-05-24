package com.troy.crypto.tracker.web.rest;

import com.troy.crypto.tracker.domain.Coin;
import com.troy.crypto.tracker.domain.UserCoin;
import com.troy.crypto.tracker.service.CoinService;
import com.troy.crypto.tracker.service.CryptoService;
import com.troy.crypto.tracker.service.UserCoinService;
import com.troy.crypto.tracker.service.cmc.CMCResponse;
import com.troy.crypto.tracker.service.dto.UserCoinDTO;
import com.troy.crypto.tracker.service.dto.UserPortfolio;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CoinController {

    private final CoinService coinService;
    private final UserCoinService userCoinService;
    private final CryptoService cryptoService;

    public CoinController(CoinService coinService, UserCoinService userCoinService, CryptoService cryptoService) {
        this.coinService = coinService;
        this.userCoinService = userCoinService;
        this.cryptoService = cryptoService;
    }

    // TODO: DELETE!!
    @GetMapping("/init/coins")
    public void init() {
        coinService.deleteAll();
        userCoinService.deleteAll();
        coinService.createTestCoins();
        userCoinService.createTestUserCoins();
    }

    @GetMapping("/coins/{symbol}")
    public Coin getCoinBySymbol(@PathVariable String symbol) {
        return coinService.findBySymbol(symbol);
    }

    @GetMapping("/coins")
    public List<Coin> getCoins() {
        refreshCommonListings();
        return coinService.findAll();
    }

    @GetMapping("/coins/listings/latest")
    public String getLatestListings() {
        return cryptoService.getLatestListings();
    }

    @GetMapping("/coins/listings/common")
    public CMCResponse refreshCommonListings() {
        CMCResponse commonListings = cryptoService.getCommonListings();
        coinService.refreshCoinPrices(commonListings);
        return commonListings;
    }

    @GetMapping("/users/{username}/coins")
    public List<UserCoin> getALlUserCoins(@PathVariable String username) {
        return userCoinService.findByUsername(username);
    }

    @GetMapping("/users/{username}/portfolio")
    public UserPortfolio getUserPortfolio(@PathVariable String username) {
        UserPortfolio userPortfolio = new UserPortfolio();
        List<UserCoinDTO> userCoins = userCoinService.getUserCoins(username);
        userPortfolio.setUserCoins(userCoins);
        userPortfolio.setPortfolioTotal(
            userCoins
                .stream()
                .map(userCoinDTO -> userCoinDTO.getCurrentCost().multiply(userCoinDTO.getQuantity()))
                .reduce(new BigDecimal("0"), BigDecimal::add)
        );
        userPortfolio.setPortfolioTotalGainLoss(
            userCoins.stream().map(UserCoinDTO::getGainLoss).reduce(new BigDecimal("0"), BigDecimal::add)
        );
        return userPortfolio;
    }

    @GetMapping("/users/{username}/coins/{symbol}")
    public List<UserCoin> getUserCoinBySymbol(@PathVariable String username, @PathVariable String symbol) {
        return userCoinService.findByUsernameAndCoinSymbol(username, symbol);
    }
}
