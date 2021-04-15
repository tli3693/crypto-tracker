package com.troy.crypto.tracker.domain;

import java.math.BigDecimal;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserCoin {

    @Id
    private String id;

    private String coinSymbol;
    private BigDecimal cost;
    private BigDecimal quantity;
    private Date actionDate;
    private String username;
    private ActionType action;

    private String portfolio;
    private String tradeId;
    private String product;
    private BigDecimal price;
    private BigDecimal fee;
    private String currency;

    public UserCoin() {}

    public UserCoin(String coinSymbol, BigDecimal cost, BigDecimal quantity, Date actionDate, String username, ActionType action) {
        this.coinSymbol = coinSymbol;
        this.cost = cost;
        this.quantity = quantity;
        this.actionDate = actionDate;
        this.username = username;
        this.action = action;
    }

    public String getCoinSymbol() {
        return coinSymbol;
    }

    public void setCoinSymbol(String coinSymbol) {
        this.coinSymbol = coinSymbol;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public ActionType getAction() {
        return action;
    }

    public void setAction(ActionType action) {
        this.action = action;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return (
            "UserCoin{" +
            "id='" +
            id +
            '\'' +
            ", coinSymbol='" +
            coinSymbol +
            '\'' +
            ", cost=" +
            cost +
            ", quantity=" +
            quantity +
            ", actionDate=" +
            actionDate +
            ", username='" +
            username +
            '\'' +
            ", action=" +
            action +
            ", portfolio='" +
            portfolio +
            '\'' +
            ", tradeId='" +
            tradeId +
            '\'' +
            ", product='" +
            product +
            '\'' +
            ", price=" +
            price +
            ", fee=" +
            fee +
            ", currency='" +
            currency +
            '\'' +
            '}'
        );
    }
}
