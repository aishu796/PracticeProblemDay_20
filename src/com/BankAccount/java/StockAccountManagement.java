package com.BankAccount.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Stock {
    private String stockName;
    private int numberOfShares;
    private double sharePrice;

    public Stock(String stockName, int numberOfShares, double sharePrice) {
        this.stockName = stockName;
        this.numberOfShares = numberOfShares;
        this.sharePrice = sharePrice;
    }

    public double calculateStockValue() {
        return numberOfShares * sharePrice;
    }

    public String getStockName() {
        return stockName;
    }

    public int getNumberOfShares() {
        return numberOfShares;
    }

    public double getSharePrice() {
        return sharePrice;
    }
}

class StockPortfolio {
    private List<Stock> stocks;

    public StockPortfolio() {
        stocks = new ArrayList<>();
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public double calculateTotalValue() {
        double totalValue = 0;
        for (Stock stock : stocks) {
            totalValue += stock.calculateStockValue();
        }
        return totalValue;
    }

    public void printStockReport() {
        System.out.println("Stock Report:");
        for (Stock stock : stocks) {
            System.out.println("Stock Name: " + stock.getStockName());
            System.out.println("Number of Shares: " + stock.getNumberOfShares());
            System.out.println("Share Price: $" + stock.getSharePrice());
            System.out.println("Stock Value: $" + stock.calculateStockValue());
            System.out.println();
        }
        System.out.println("Total Stock Value: $" + calculateTotalValue());
    }
}

public class StockAccountManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of stocks: ");
        int numStocks = scanner.nextInt();

        StockPortfolio stockPortfolio = new StockPortfolio();

        for (int i = 0; i < numStocks; i++) {
            System.out.println("Stock " + (i + 1));
            System.out.print("Enter Stock Name: ");
            String stockName = scanner.next();

            System.out.print("Enter Number of Shares: ");
            int numberOfShares = scanner.nextInt();

            System.out.print("Enter Share Price: ");
            double sharePrice = scanner.nextDouble();

            Stock stock = new Stock(stockName, numberOfShares, sharePrice);
            stockPortfolio.addStock(stock);
            System.out.println();
        }

        stockPortfolio.printStockReport();

        scanner.close();
    }
}

