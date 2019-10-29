package com.ge.exercise3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Account {

    private static final Logger logger = LogManager.getLogger(Account.class);

    private float monthlyInterestRate = 1.01f;
    private float monthlyFee = 0.0f;

    private String accountNumber;
    private String accountType;
    private float balance;

    public Account(String accountNumber, String accountType, float balance) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        if (accountType == "Checking") {
            monthlyInterestRate = 1.0f;
        }
    }

    public Account(String accountNumber, String accountType) {
        this(accountNumber, accountType, 0.0f);
    }

    public Account(String accountNumber) {
        this(accountNumber, "Savings", 0.0f);
    }

    public float valueNextMonth() {
        return (this.balance * this.monthlyInterestRate) - this.monthlyFee;
    }

    @Override
    public String toString() {
        if (this.accountType == "Checking") {
            if (this.monthlyFee == 0.0f) {
                return "No fee checking account #" + this.accountNumber;
            } else {
                return "Checking account #" + this.accountNumber;
            }
        } else {
            if (this.monthlyInterestRate > 1.01) {
                if (this.monthlyFee == 0.0f) {
                    return "High interest no fee savings account #" + this.accountNumber;
                } else {
                    return "High interest savings account #" + this.accountNumber;
                }
            } else {
                if (this.monthlyFee == 0.0f) {
                    return "No fee savings account #" + this.accountNumber;
                } else {
                    return "Savings account #" + this.accountNumber;
                }
            }
        }
    }

    public void deposit(float amount) {
        this.balance += amount;
    }

    public void withdraw(float amount) {
        if(accountType.equals("Checking")) {
            if(amount <= 100.0f) {
               this.balance = this.balance - amount;
            }
        } else {
            if (this.balance - amount > 0) {
                this.balance = this.balance - amount;
            }
        }
    }

    public float getMonthlyInterestRate() {
        return this.monthlyInterestRate;
    }

    public void setMonthlyInterestRate(float monthlyInterestRate) {
        this.monthlyInterestRate = monthlyInterestRate;
    }

    public float getMonthlyFee() {
        return this.monthlyFee;
    }

    public void setMonthlyFee(float monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public float getBalance() {
        return this.balance;
    }

    void setBalance(float balance) {
        this.balance = balance;
    }
}
