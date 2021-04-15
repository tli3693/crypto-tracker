package com.troy.crypto.tracker.service.cmc;

import java.util.Date;

public class CMCCoin {

    private Long id;
    private String name;
    private String symbol;
    private Date last_updated;
    private Long max_supply;
    private CMCQuote quote;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Date getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(Date last_updated) {
        this.last_updated = last_updated;
    }

    public Long getMax_supply() {
        return max_supply;
    }

    public void setMax_supply(Long max_supply) {
        this.max_supply = max_supply;
    }

    public CMCQuote getQuote() {
        return quote;
    }

    public void setQuote(CMCQuote quote) {
        this.quote = quote;
    }
    /*
	"id": 1,
            "name": "Bitcoin",
            "symbol": "BTC",
            "slug": "bitcoin",
            "max_supply": 21000000,
            "last_updated": "2021-04-14T23:59:02.000Z",
            "quote": {
                "USD": {
                    "price": 63109.69593463581,
                    "volume_24h": 77451779687.03375,
                    "percent_change_1h": 0.19922652,
                    "percent_change_24h": -0.3872175,
                    "percent_change_7d": 11.52690536,
                    "percent_change_30d": 12.280865,
                    "percent_change_60d": 33.9869688,
                    "percent_change_90d": 62.05173902,
                    "market_cap": 1179061093980.419,
                    "last_updated": "2021-04-14T23:59:02.000Z"
                }
            }
        }
	 */
}
