package com.troy.crypto.tracker.service.pro;

import java.math.BigDecimal;
import java.util.Date;

public class CoinbaseCSVRecord {

    private Date date;
    private String transactionType;
    private String asset;
    private BigDecimal quantity;
    private BigDecimal price;
    private BigDecimal subtotal;
    private BigDecimal total;
    private BigDecimal fees;
    private String notes;

    public CoinbaseCSVRecord(
        Date date,
        String transactionType,
        String asset,
        BigDecimal quantity,
        BigDecimal price,
        BigDecimal subtotal,
        BigDecimal total,
        BigDecimal fees,
        String notes
    ) {
        this.date = date;
        this.transactionType = transactionType;
        this.asset = asset;
        this.quantity = quantity;
        this.price = price;
        this.subtotal = subtotal;
        this.total = total;
        this.fees = fees;
        this.notes = notes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getFees() {
        return fees;
    }

    public void setFees(BigDecimal fees) {
        this.fees = fees;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return (
            "CoinbaseCSVRecord{" +
            "date=" +
            date +
            ", transactionType='" +
            transactionType +
            '\'' +
            ", asset='" +
            asset +
            '\'' +
            ", quantity=" +
            quantity +
            ", price=" +
            price +
            ", subtotal=" +
            subtotal +
            ", total=" +
            total +
            ", fees=" +
            fees +
            ", notes='" +
            notes +
            '\'' +
            '}'
        );
    }
}
