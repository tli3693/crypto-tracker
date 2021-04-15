package com.troy.crypto.tracker.service.cmc;

public class CMCUSDQuote {

    private String price;
    private String volume_24h;
    private String percent_change_1h;
    private String percent_change_24h;
    private String percent_change_7d;
    private String percent_change_30d;
    private String percent_change_60d;
    private String percent_change_90d;
    private String market_cap;
    private String last_updated;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVolume_24h() {
        return volume_24h;
    }

    public void setVolume_24h(String volume_24h) {
        this.volume_24h = volume_24h;
    }

    public String getPercent_change_1h() {
        return percent_change_1h;
    }

    public void setPercent_change_1h(String percent_change_1h) {
        this.percent_change_1h = percent_change_1h;
    }

    public String getPercent_change_24h() {
        return percent_change_24h;
    }

    public void setPercent_change_24h(String percent_change_24h) {
        this.percent_change_24h = percent_change_24h;
    }

    public String getPercent_change_7d() {
        return percent_change_7d;
    }

    public void setPercent_change_7d(String percent_change_7d) {
        this.percent_change_7d = percent_change_7d;
    }

    public String getPercent_change_30d() {
        return percent_change_30d;
    }

    public void setPercent_change_30d(String percent_change_30d) {
        this.percent_change_30d = percent_change_30d;
    }

    public String getPercent_change_60d() {
        return percent_change_60d;
    }

    public void setPercent_change_60d(String percent_change_60d) {
        this.percent_change_60d = percent_change_60d;
    }

    public String getPercent_change_90d() {
        return percent_change_90d;
    }

    public void setPercent_change_90d(String percent_change_90d) {
        this.percent_change_90d = percent_change_90d;
    }

    public String getMarket_cap() {
        return market_cap;
    }

    public void setMarket_cap(String market_cap) {
        this.market_cap = market_cap;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }
}
