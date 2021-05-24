package com.troy.crypto.tracker.service.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class UserPortfolio {

    private BigDecimal portfolioTotal;
    private BigDecimal portfolioTotalGainLoss;
    private BigDecimal portfolioTotalGainLossPercentage;
    private List<UserCoinDTO> userCoins;

    public List<UserCoinDTO> getUserCoins() {
        return userCoins;
    }

    public void setUserCoins(List<UserCoinDTO> userCoins) {
        this.userCoins = userCoins;
    }

    public BigDecimal getPortfolioTotal() {
        return portfolioTotal;
    }

    public void setPortfolioTotal(BigDecimal portfolioTotal) {
        this.portfolioTotal = portfolioTotal;
    }

    public BigDecimal getPortfolioTotalGainLoss() {
        return portfolioTotalGainLoss;
    }

    public void setPortfolioTotalGainLoss(BigDecimal portfolioTotalGainLoss) {
        this.portfolioTotalGainLoss = portfolioTotalGainLoss;
    }

    public BigDecimal getPortfolioTotalGainLossPercentage() {
        BigDecimal originalSpent = portfolioTotal.subtract(portfolioTotalGainLoss);
        portfolioTotalGainLossPercentage = portfolioTotalGainLoss.divide(originalSpent, 4, RoundingMode.FLOOR);
        return portfolioTotalGainLossPercentage;
    }
}
