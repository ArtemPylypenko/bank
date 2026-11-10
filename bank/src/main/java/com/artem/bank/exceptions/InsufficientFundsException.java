package com.artem.bank.exceptions;

import java.math.BigDecimal;

public class InsufficientFundsException extends RuntimeException {
    private final BigDecimal balance;
    private final BigDecimal requiredAmount;

    public InsufficientFundsException(BigDecimal balance, BigDecimal requiredAmount) {
        super("Balance = " + balance + " is not enough, should be at least " + requiredAmount);
        this.balance = balance;
        this.requiredAmount = requiredAmount;
    }

    public InsufficientFundsException(BigDecimal balance, BigDecimal requiredAmount, Throwable cause) {
        super("Balance = " + balance + " is not enough, should be at least " + requiredAmount, cause);
        this.balance = balance;
        this.requiredAmount = requiredAmount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal getRequiredAmount() {
        return requiredAmount;
    }
}
