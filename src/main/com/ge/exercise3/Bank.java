package com.ge.exercise3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    private static final Logger logger = LogManager.getLogger(Bank.class);
    private final String PROFIT = "PROFIT";
    private final String LOSS = "LOSS";
    private Map<String, Account> accountMap;

    public Bank() {
        accountMap = new HashMap<>();
    }

    public Account getAccount(String accountNumber) {
        return accountMap.get(accountNumber);
    }

    public void addAccount(Account account) {
        accountMap.put(account.getAccountNumber(), account);
    }

    public void depositToAccount(String accountNumber, float amount) {
        getAccount(accountNumber).deposit(amount);
    }

    public void withdrawFromAccount(String accountNumber, float amount) {
        getAccount(accountNumber).withdraw(amount);
    }

    public int numAccounts() {
        return accountMap.size();
    }

    public float totalBalance() {
        float sum = 0.0f;
        for(Map.Entry<String, Account> accountEntry : accountMap.entrySet()) {
            Account account = accountEntry.getValue();
            sum = sum + account.getBalance();
        }
        return sum;
    }

    public String calculateProfitOrLoss() {
        float sumOfAllFees = 0.0f;
        float totalInterestPaid = 0.0f;
        for(Map.Entry<String, Account> accountEntry : accountMap.entrySet()) {
            Account account = accountEntry.getValue();
            sumOfAllFees = sumOfAllFees + account.getBalance();
            totalInterestPaid = totalInterestPaid + (account.getBalance() * account.getMonthlyInterestRate());
        }
        if (sumOfAllFees > totalInterestPaid)
            return PROFIT;
        else
            return LOSS;
    }
}
