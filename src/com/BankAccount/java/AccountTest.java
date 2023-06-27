package com.BankAccount.java;

class Account {
    private double balance;

    public Account(double initialBalance) {
        if (initialBalance >= 0) {
            balance = initialBalance;
        } else {
            balance = 0;
            System.out.println("Initial balance cannot be negative. Setting balance to 0.");
        }
    }

    public double getBalance() {
        return balance;
    }

    public void credit(double amount) {
        if (amount >= 0) {
            balance += amount;
        } else {
            System.out.println("Credit amount cannot be negative.");
        }
    }

    public void debit(double amount) {
        if (amount >= 0) {
            if (amount <= balance) {
                balance -= amount;
                System.out.println("Debit of $" + amount + " successful.");
            } else {
                System.out.println("Debit amount exceeded account balance.");
            }
        } else {
            System.out.println("Debit amount cannot be negative.");
        }
    }
}

public class AccountTest {
    public static void main(String[] args) {
        Account account = new Account(1000);

        System.out.println("Initial balance: $" + account.getBalance());

        account.credit(500);
        System.out.println("Balance after credit: $" + account.getBalance());

        account.debit(200);
        System.out.println("Balance after debit: $" + account.getBalance());

        account.debit(1500);
        System.out.println("Balance after invalid debit: $" + account.getBalance());
    }
}

