package com.artem.bank.entity;

import com.artem.bank.exceptions.InsufficientFundsException;

import java.math.BigDecimal;
import java.util.Objects;

public record Money(BigDecimal amount, String currencyCode) {

    public Money {
        Objects.requireNonNull(amount, "amount");
        Objects.requireNonNull(currencyCode, "currencyCode");

        if (amount.signum() < 0) {
            throw new IllegalArgumentException("Amount must not be negative: " + amount);
        }
        if (amount.scale() != 2) {
            throw new IllegalArgumentException("Amount must have 2 decimal places: " + amount);
        }
        if (currencyCode.isBlank()) {
            throw new IllegalArgumentException("Currency must not be blank");
        }

    }

    public Money subtract(Money a) {
        BigDecimal newAmount;
        if (!a.currencyCode.equals(this.currencyCode)) {
            throw new IllegalArgumentException("Currency mismatch: " + this.currencyCode + " vs " + a.currencyCode);
        }
        if (this.amount.compareTo(a.amount) < 0) {
            throw new InsufficientFundsException(this.amount, a.amount);
        }
        newAmount = amount.subtract(a.amount);
        return new Money(newAmount, a.currencyCode);
    }
}
