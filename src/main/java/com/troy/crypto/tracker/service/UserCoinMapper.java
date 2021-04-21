package com.troy.crypto.tracker.service;

import com.troy.crypto.tracker.domain.UserCoin;
import com.troy.crypto.tracker.service.dto.UserCoinDTO;
import com.troy.crypto.tracker.service.pro.CoinbaseCSVRecord;
import com.troy.crypto.tracker.service.pro.CoinbaseProCSVRecord;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface UserCoinMapper {
    UserCoinDTO toDTO(UserCoin userCoin);

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
