package com.artem.bank;

import com.artem.bank.entity.Money;
import com.artem.bank.exceptions.InsufficientFundsException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class MoneyTest {
    @Test
    void equalMoneyAreEqualAndHaveSameHashCode() {
        Money a = new Money(new BigDecimal("100.00"), "UAH");
        Money b = new Money(new BigDecimal("100.00"), "UAH");

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void differentCurrencyIsNotEqual() {
        assertNotEquals(
                new Money(new BigDecimal("100.00"), "UAH"),
                new Money(new BigDecimal("100.00"), "USD"));
    }

    @Test
    void equalMoneyIsStoredOnceInHashSet() {
        Set<Money> set = new HashSet<>();
        set.add(new Money(new BigDecimal("100.00"), "UAH"));
        set.add(new Money(new BigDecimal("100.00"), "UAH"));

        assertEquals(1, set.size());
    }

    @Test
    void subtractReturnsDifference() {
        Money a = new Money(new BigDecimal("1100.00"), "UAH");
        Money b = new Money(new BigDecimal("1000.00"), "UAH");
        Money res = a.subtract(b);
        assertNotNull(res);
        assertEquals(new Money(new BigDecimal("100.00"), "UAH"), res);
    }

    @Test
    void subtractMoreThanBalanceThrows() {
        Money a = new Money(new BigDecimal("900.00"), "UAH");
        Money b = new Money(new BigDecimal("1000.00"), "UAH");

        InsufficientFundsException e = assertThrows(InsufficientFundsException.class, () -> a.subtract(b));

        assertEquals(a.amount(), e.getBalance());
        assertEquals(b.amount(), e.getRequiredAmount());
    }

    @Test
    void subtractCanReturnAmountBelowOneHundred() {
        Money result = new Money(new BigDecimal("150.00"), "UAH")
                .subtract(new Money(new BigDecimal("100.00"), "UAH"));

        assertEquals(new Money(new BigDecimal("50.00"), "UAH"), result);
    }

    @Test
    void subtractCanReturnZero() {
        Money result = new Money(new BigDecimal("100.00"), "UAH")
                .subtract(new Money(new BigDecimal("100.00"), "UAH"));

        assertEquals(new Money(new BigDecimal("0.00"), "UAH"), result);
    }

    @Test
    void subtractWithDifferentCurrencyThrows() {
        Money uah = new Money(new BigDecimal("100.00"), "UAH");
        Money usd = new Money(new BigDecimal("10.00"), "USD");

        assertThrows(IllegalArgumentException.class, () -> uah.subtract(usd));
    }

    @Test
    void validAmountBelowOneHundredIsAccepted() {
        Money money = new Money(new BigDecimal("50.00"), "UAH");

        assertEquals(new BigDecimal("50.00"), money.amount());
        assertEquals("UAH", money.currencyCode());
    }

    @Test
    void nullAmountThrows() {
        assertThrows(NullPointerException.class, () -> new Money(null, "UAH"));
    }

    @Test
    void nullCurrencyThrows() {
        assertThrows(NullPointerException.class, () -> new Money(new BigDecimal("100.00"), null));
    }

    @Test
    void negativeAmountThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Money(new BigDecimal("-0.01"), "UAH"));
    }

    @Test
    void emptyCurrencyThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Money(new BigDecimal("100.00"), ""));
    }

    @Test
    void blankCurrencyThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Money(new BigDecimal("100.00"), "   "));
    }

    @Test
    void amountWithoutDecimalPlacesThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Money(new BigDecimal("100"), "UAH"));
    }

    @Test
    void amountWithThreeDecimalPlacesThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Money(new BigDecimal("100.123"), "UAH"));
    }
}
