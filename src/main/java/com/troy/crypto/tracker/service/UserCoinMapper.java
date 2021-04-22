package com.troy.crypto.tracker.service;

import com.troy.crypto.tracker.domain.Coin;
import com.troy.crypto.tracker.domain.UserCoin;
import com.troy.crypto.tracker.service.cmc.CMCCoin;
import com.troy.crypto.tracker.service.cmc.CMCUSDQuote;
import com.troy.crypto.tracker.service.dto.UserCoinDTO;
import com.troy.crypto.tracker.service.pro.CoinbaseCSVRecord;
import com.troy.crypto.tracker.service.pro.CoinbaseProCSVRecord;
import java.math.BigDecimal;
import java.util.List;
import org.mapstruct.*;

@Mapper
public interface UserCoinMapper {
    UserCoinDTO toDTO(UserCoin userCoin);

    @Mappings({ @Mapping(target = "id", ignore = true), @Mapping(target = "lastUpdated", source = "last_updated") })
    Coin toCoin(CMCCoin cmcCoin);

    @AfterMapping
    default void afterMappingCoin(@MappingTarget Coin coin, CMCCoin cmcCoin) {
        CMCUSDQuote usdQuote = cmcCoin.getQuote().getUSD();
        coin.setCost(new BigDecimal(usdQuote.getPrice()));
        coin.setPercent_change_24h(new BigDecimal(usdQuote.getPercent_change_24h()));
    }

    @Mappings(
        {
            @Mapping(target = "coinSymbol", source = "sizeUnit"),
            @Mapping(target = "cost", source = "total"),
            @Mapping(target = "quantity", source = "size"),
            @Mapping(target = "actionDate", source = "createdAt"),
            @Mapping(target = "action", source = "side"),
        }
    )
    UserCoin toDataModel(CoinbaseProCSVRecord coinbaseProCSVRecord);

    @Mappings(
        {
            @Mapping(target = "coinSymbol", source = "asset"),
            @Mapping(target = "cost", source = "total"),
            @Mapping(target = "actionDate", source = "date"),
            @Mapping(target = "action", source = "transactionType"),
            @Mapping(target = "tradeId", source = "notes"),
        }
    )
    UserCoin toDataModel(CoinbaseCSVRecord coinbaseCSVRecord);

    List<UserCoin> toDataModelList(List<CoinbaseProCSVRecord> coinbaseProCSVRecord);

    List<UserCoin> toCoinbaseCSVRecordList(List<CoinbaseCSVRecord> coinbaseProCSVRecord);
}
