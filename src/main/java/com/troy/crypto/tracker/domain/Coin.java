package com.troy.crypto.tracker.domain;

import java.math.BigDecimal;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Coin {

    @Id
    private String id;

    private String name;
    private String symbol;
    private BigDecimal cost;
    private Date lastUpdated;
    private BigDecimal percent_change_24h;

    public Coin() {}

    public Coin(String name, String symbol, BigDecimal cost, Date lastUpdated, BigDecimal percent_change_24h) {
        this.name = name;
        this.symbol = symbol;
        this.cost = cost;
        this.lastUpdated = lastUpdated;
        this.percent_change_24h = percent_change_24h;
    }

    public Coin(String name, String symbol, BigDecimal cost) {
        this.name = name;
        this.symbol = symbol;
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Coin{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", symbol='" + symbol + '\'' + ", cost=" + cost + '}';
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public BigDecimal getPercent_change_24h() {
        return percent_change_24h;
    }

    public void setPercent_change_24h(BigDecimal percent_change_24h) {
        this.percent_change_24h = percent_change_24h;
    }
}
