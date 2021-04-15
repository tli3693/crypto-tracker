package com.troy.crypto.tracker.service.cmc;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CMCData {

    @JsonProperty(value = "BTC")
    private CMCCoin BTC;

    @JsonProperty(value = "ETH")
    private CMCCoin ETH;

    @JsonProperty(value = "LTC")
    private CMCCoin LTC;

    @JsonProperty(value = "ADA")
    private CMCCoin ADA;

    public CMCCoin getBTC() {
        return BTC;
    }

    public void setBTC(CMCCoin BTC) {
        this.BTC = BTC;
    }

    public CMCCoin getETH() {
        return ETH;
    }

    public void setETH(CMCCoin ETH) {
        this.ETH = ETH;
    }

    public CMCCoin getLTC() {
        return LTC;
    }

    public void setLTC(CMCCoin LTC) {
        this.LTC = LTC;
    }

    public CMCCoin getADA() {
        return ADA;
    }

    public void setADA(CMCCoin ADA) {
        this.ADA = ADA;
    }
}
