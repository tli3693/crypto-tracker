package com.troy.crypto.tracker.service;

import com.troy.crypto.tracker.domain.ActionType;
import com.troy.crypto.tracker.domain.Coin;
import com.troy.crypto.tracker.domain.UserCoin;
import com.troy.crypto.tracker.repository.UserCoinRepository;
import com.troy.crypto.tracker.service.dto.UserCoinDTO;
import com.troy.crypto.tracker.service.pro.CoinbaseCSVRecord;
import com.troy.crypto.tracker.service.pro.CoinbaseProCSVRecord;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCoinService {

    private final UserCoinRepository userCoinRepository;
    private final UserCoinMapper userCoinMapper;
    private final CSVService csvService;

    @Autowired
    private CoinService coinService;

    @Autowired
    public UserCoinService(UserCoinRepository userCoinRepository, UserCoinMapper userCoinMapper, CSVService csvService) {
        this.userCoinRepository = userCoinRepository;
        this.userCoinMapper = userCoinMapper;
        this.csvService = csvService;
    }

    public void deleteAll() {
        userCoinRepository.deleteAll();
    }

    public List<UserCoin> findByUsername(String username) {
        return userCoinRepository.findByUsername(username);
    }

    public List<UserCoinDTO> getUserCoins(String username) {
        List<UserCoinDTO> userCoinDTOS = new ArrayList<>();
        List<UserCoin> userCoins = findByUsername(username);
        List<String> coinSymbols = userCoins.stream().map(UserCoin::getCoinSymbol).distinct().collect(Collectors.toList());
        for (String coinSymbol : coinSymbols) {
            Coin coin = coinService.findBySymbol(coinSymbol);
            List<UserCoin> userCoinList = userCoins
                .stream()
                .filter(userCoin -> coinSymbol.equals(userCoin.getCoinSymbol()))
                .collect(Collectors.toList());
            UserCoinDTO userCoinDTO = userCoinMapper.toDTO(userCoinList.get(0));
            userCoinDTOS.add(userCoinDTO);
            BigDecimal total = userCoinList.stream().map(UserCoin::getCost).reduce(new BigDecimal("0"), BigDecimal::add);
            userCoinDTO.setTotal(total);
            BigDecimal quantity = userCoinList.stream().map(UserCoin::getQuantity).reduce(new BigDecimal("0"), BigDecimal::add);
            BigDecimal feesTotal = userCoinList.stream().map(UserCoin::getFee).reduce(new BigDecimal("0"), BigDecimal::add);
            userCoinDTO.setQuantity(quantity);
            userCoinDTO.setAverageCost((total.subtract(feesTotal)).divide(quantity, 2, RoundingMode.FLOOR));
            userCoinDTO.setGainLoss(coin.getCost().multiply(quantity).subtract(total));
            userCoinDTO.setCurrentCost(coin.getCost());
        }

        return userCoinDTOS;
    }

    public List<UserCoin> findByUsernameAndCoinSymbol(String username, String symbol) {
        return userCoinRepository.findByUsernameAndCoinSymbol(username, symbol);
    }

    public List<UserCoin> uploadCoinbaseProCSV(String fileName, String username) throws IOException, ParseException {
        List<CoinbaseProCSVRecord> coinbaseProCSVRecords = csvService.parseCoinbaseProCSV(fileName, username);
        List<UserCoin> userCoins = userCoinMapper.toDataModelList(coinbaseProCSVRecords);
        for (UserCoin userCoin : userCoins) {
            UserCoin userCoinByTradeId = userCoinRepository.findByTradeIdAndUsername(userCoin.getTradeId(), username);
            if (userCoinByTradeId == null) {
                userCoinRepository.save(userCoin);
            }
        }
        return userCoins;
    }

    public List<UserCoin> uploadCoinbaseCSV(String fileName, String username) throws IOException, ParseException {
        List<CoinbaseCSVRecord> coinbaseProCSVRecords = csvService.parseCoinbaseCSV(fileName, username);
        List<UserCoin> userCoins = userCoinMapper.toCoinbaseCSVRecordList(coinbaseProCSVRecords);
        saveCoins(userCoins, username);
        return userCoins;
    }

    private void saveCoins(List<UserCoin> userCoins, String username) {
        for (UserCoin userCoin : userCoins) {
            UserCoin userCoinByTradeId = userCoinRepository.findByTradeIdAndUsername(userCoin.getTradeId(), username);
            if (userCoinByTradeId == null) {
                userCoinRepository.save(userCoin);
            }
        }
    }

    public void createTestUserCoins() {
        System.out.println("Creating user coins");
        userCoinRepository.insert(
            new UserCoin("BTC", new BigDecimal("2921.09"), new BigDecimal("6"), new Date(), "tli3693@gmail.com", ActionType.BUY)
        );
        userCoinRepository.insert(
            new UserCoin("BTC", new BigDecimal("2231.22"), new BigDecimal("2"), new Date(), "tli3693@gmail.com", ActionType.BUY)
        );
        userCoinRepository.insert(
            new UserCoin("LTC", new BigDecimal("241.99"), new BigDecimal("1"), new Date(), "tli3693@gmail.com", ActionType.BUY)
        );
        userCoinRepository.insert(
            new UserCoin("ETH", new BigDecimal("2597.05"), new BigDecimal("3"), new Date(), "tli3693@gmail.com", ActionType.BUY)
        );
        userCoinRepository.insert(
            new UserCoin("BTC", new BigDecimal("55143.20"), new BigDecimal("4.25"), new Date(), "tli3693@gmail.com", ActionType.BUY)
        );
    }
}
