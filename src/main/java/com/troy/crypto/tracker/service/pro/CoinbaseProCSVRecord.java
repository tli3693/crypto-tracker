package com.troy.crypto.tracker.service.pro;

import java.math.BigDecimal;
import java.util.Date;

public class CoinbaseProCSVRecord {

    private String portfolio;
    private String tradeId;
    private String product;
    private String side;
    private Date createdAt;
    private BigDecimal size;
    private String sizeUnit;
    private BigDecimal price;
    private BigDecimal fee;
    private BigDecimal total;
    private String currency;

    public CoinbaseProCSVRecord(
        String portfolio,
        String tradeId,
        String product,
        String side,
        Date createdAt,
        BigDecimal size,
        String sizeUnit,
        BigDecimal price,
        BigDecimal fee,
        BigDecimal total,
        String currency
    ) {
        this.portfolio = portfolio;
        this.tradeId = tradeId;
        this.product = product;
        this.side = side;
        this.createdAt = createdAt;
        this.size = size;
        this.sizeUnit = sizeUnit;
        this.price = price;
        this.fee = fee;
        this.total = total;
        this.currency = currency;
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

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }

    public String getSizeUnit() {
        return sizeUnit;
    }

    public void setSizeUnit(String sizeUnit) {
        this.sizeUnit = sizeUnit;
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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
            "CoinbaseProCSVRecord{" +
            "portfolio='" +
            portfolio +
            '\'' +
            ", tradeId='" +
            tradeId +
            '\'' +
            ", product='" +
            product +
            '\'' +
            ", side='" +
            side +
            '\'' +
            ", createdAt='" +
            createdAt +
            '\'' +
            ", size='" +
            size +
            '\'' +
            ", sizeUnit='" +
            sizeUnit +
            '\'' +
            ", price='" +
            price +
            '\'' +
            ", fee='" +
            fee +
            '\'' +
            ", total='" +
            total +
            '\'' +
            ", currency='" +
            currency +
            '\'' +
            '}'
        );
    }
}
