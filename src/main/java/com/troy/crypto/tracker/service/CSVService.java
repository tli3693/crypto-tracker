package com.troy.crypto.tracker.service;

import com.troy.crypto.tracker.service.fileupload.StorageService;
import com.troy.crypto.tracker.service.pro.CoinbaseCSVRecord;
import com.troy.crypto.tracker.service.pro.CoinbaseProCSVRecord;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

@Service
public class CSVService {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
    private final SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");

    private final StorageService storageService;

    public CSVService(StorageService storageService) {
        this.storageService = storageService;
    }

    public List<CoinbaseProCSVRecord> parseCoinbaseProCSV(String fileName) throws IOException, ParseException {
        List<CoinbaseProCSVRecord> coinbaseProCSVRecords = new ArrayList<>();
        Reader in = new FileReader(storageService.loadAsResource(fileName).getFile());
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
        for (CSVRecord record : records) {
            CoinbaseProCSVRecord coinbaseProCSVRecord = new CoinbaseProCSVRecord(
                record.get("portfolio"),
                record.get("trade id"),
                record.get("product"),
                record.get("side"),
                dateFormat.parse(record.get("created at")),
                new BigDecimal(record.get("size")),
                record.get("size unit"),
                new BigDecimal(record.get("price")),
                new BigDecimal(record.get("fee")),
                new BigDecimal(record.get("total")).negate(),
                record.get("price/fee/total unit")
            );
            coinbaseProCSVRecords.add(coinbaseProCSVRecord);
        }
        return coinbaseProCSVRecords;
    }

    public List<CoinbaseCSVRecord> parseCoinbaseCSV(String fileName) throws IOException, ParseException {
        List<CoinbaseCSVRecord> coinbaseCSVRecords = new ArrayList<>();
        Reader in = new FileReader(storageService.loadAsResource(fileName).getFile());
        Iterable<CSVRecord> records = CSVFormat.RFC4180
            .withHeader(
                "Timestamp",
                "Transaction Type",
                "Asset",
                "Quantity Transacted",
                "USD Spot Price at Transaction",
                "USD Subtotal",
                "USD Total (inclusive of fees)",
                "USD Fees",
                "Notes"
            )
            .parse(in);
        for (int i = 0; i < 8; i++) {
            if (records.iterator().hasNext()) {
                records.iterator().next();
            }
        }
        for (CSVRecord record : records) {
            CoinbaseCSVRecord coinbaseCSVRecord = new CoinbaseCSVRecord(
                dateFormat2.parse(record.get("Timestamp")),
                record.get("Transaction Type").toUpperCase(),
                record.get("Asset").toUpperCase(),
                new BigDecimal(record.get("Quantity Transacted")),
                new BigDecimal(record.get("USD Spot Price at Transaction")),
                new BigDecimal(record.get("USD Subtotal").isEmpty() ? "0.00" : record.get("USD Subtotal")),
                new BigDecimal(
                    record.get("USD Total (inclusive of fees)").isEmpty() ? "0.00" : record.get("USD Total (inclusive of fees)")
                ),
                new BigDecimal(record.get("USD Fees").isEmpty() ? "0.00" : record.get("USD Fees")),
                record.get("Notes")
            );
            coinbaseCSVRecords.add(coinbaseCSVRecord);
        }
        return coinbaseCSVRecords;
    }
}
