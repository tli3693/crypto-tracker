package com.troy.crypto.tracker.service.dto;

import java.math.BigDecimal;

public class UserCoinDTO {

    private String coinSymbol;
    private BigDecimal total;
    private BigDecimal quantity;
    private BigDecimal averageCost;
    private BigDecimal gainLoss;
    private String username;
    private BigDecimal currentCost;

    public String getCoinSymbol() {
        return coinSymbol;
    }

    public void setCoinSymbol(String coinSymbol) {
        this.coinSymbol = coinSymbol;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getAverageCost() {
        return averageCost;
    }

    public void setAverageCost(BigDecimal averageCost) {
        this.averageCost = averageCost;
    }

    public BigDecimal getGainLoss() {
        return gainLoss;
    }

    public void setGainLoss(BigDecimal gainLoss) {
        this.gainLoss = gainLoss;
    }

    public BigDecimal getCurrentCost() {
        return currentCost;
    }

    public void setCurrentCost(BigDecimal currentCost) {
        this.currentCost = currentCost;
    }
}
