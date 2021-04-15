package com.troy.crypto.tracker.service.cmc;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CMCQuote {

    @JsonProperty(value = "USD")
    private CMCUSDQuote USD;

    public CMCUSDQuote getUSD() {
        return USD;
    }

    public void setUSD(CMCUSDQuote USD) {
        this.USD = USD;
    }
}
