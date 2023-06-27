package com.BankAccount.java;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class StockAccount {
    private Map<String, Integer> stocks; // Stock symbol -> Number of shares mapping
    private Map<String, Double> stockPrices; // Stock symbol -> Share price mapping
    private Map<String, CompanyShares> companyShares; // Stock symbol -> CompanyShares object mapping

    public StockAccount(String filename) {
        stocks = new HashMap<>();
        stockPrices = new HashMap<>();
        companyShares = new HashMap<>();
        loadAccountFromFile(filename);
    }

    public double valueOf() {
        double totalValue = 0;
        for (String stockSymbol : stocks.keySet()) {
            int numberOfShares = stocks.get(stockSymbol);
            double sharePrice = stockPrices.get(stockSymbol);
            totalValue += numberOfShares * sharePrice;
        }
        return totalValue;
    }

    public void buy(int amount, String symbol) {
        if (amount > 0) {
            if (stockPrices.containsKey(symbol)) {
                int currentShares = stocks.getOrDefault(symbol, 0);
                double sharePrice = stockPrices.get(symbol);
                int newShares = currentShares + amount;
                stocks.put(symbol, newShares);
                updateCompanyShares(symbol, newShares);
                System.out.println("Successfully bought " + amount + " shares of " + symbol);
            } else {
                System.out.println("Stock symbol not found. Unable to buy shares.");
            }
        } else {
            System.out.println("Invalid amount. Unable to buy shares.");
        }
    }

    public void sell(int amount, String symbol) {
        if (amount > 0) {
            if (stockPrices.containsKey(symbol)) {
                int currentShares = stocks.getOrDefault(symbol, 0);
                if (currentShares >= amount) {
                    double sharePrice = stockPrices.get(symbol);
                    int newShares = currentShares - amount;
                    stocks.put(symbol, newShares);
                    updateCompanyShares(symbol, newShares);
                    System.out.println("Successfully sold " + amount + " shares of " + symbol);
                } else {
                    System.out.println("Insufficient shares. Unable to sell.");
                }
            } else {
                System.out.println("Stock symbol not found. Unable to sell shares.");
            }
        } else {
            System.out.println("Invalid amount. Unable to sell shares.");
        }
    }

    public void save(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (String stockSymbol : stocks.keySet()) {
                int numberOfShares = stocks.get(stockSymbol);
                double sharePrice = stockPrices.get(stockSymbol);
                writer.println(stockSymbol + "," + numberOfShares + "," + sharePrice);
            }
            System.out.println("Account saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("Error saving account to file: " + filename);
        }
    }

    public void printReport() {
        System.out.println("Stock Account Report:");
        for (String stockSymbol : stocks.keySet()) {
            int numberOfShares = stocks.get(stockSymbol);
            double sharePrice = stockPrices.get(stockSymbol);
            System.out.println("Stock Symbol: " + stockSymbol);
            System.out.println("Number of Shares: " + numberOfShares);
            System.out.println("Share Price: $" + sharePrice);
            System.out.println("Total Value: $" + (numberOfShares * sharePrice));
            System.out.println();
        }
        System.out.println("Total Account Value: $" + valueOf());
    }

    private void loadAccountFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String stockSymbol = parts[0];
                    int numberOfShares = Integer.parseInt(parts[1]);
                    double sharePrice = Double.parseDouble(parts[2]);
                    stocks.put(stockSymbol, numberOfShares);
                    stockPrices.put(stockSymbol, sharePrice);
                    updateCompanyShares(stockSymbol, numberOfShares);
                }
            }
            System.out.println("Account loaded from file: " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } catch (IOException e) {
            System.out.println("Error loading account from file: " + filename);
        }
    }

    private void updateCompanyShares(String symbol, int numberOfShares) {
        LocalDateTime transactionDateTime = LocalDateTime.now();
        if (companyShares.containsKey(symbol)) {
            CompanyShares companyShare = companyShares.get(symbol);
            companyShare.setNumberOfShares(numberOfShares);
            companyShare.setTransactionDateTime(transactionDateTime);
        } else {
            CompanyShares newCompanyShare = new CompanyShares(symbol, numberOfShares, transactionDateTime);
            companyShares.put(symbol, newCompanyShare);
        }
    }
}

class CompanyShares {
    private String stockSymbol;
    private int numberOfShares;
    private LocalDateTime transactionDateTime;

    public CompanyShares(String stockSymbol, int numberOfShares, LocalDateTime transactionDateTime) {
        this.stockSymbol = stockSymbol;
        this.numberOfShares = numberOfShares;
        this.transactionDateTime = transactionDateTime;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public int getNumberOfShares() {
        return numberOfShares;
    }

    public void setNumberOfShares(int numberOfShares) {
        this.numberOfShares = numberOfShares;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }
}

