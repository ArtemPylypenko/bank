package com.artem.bank;

import com.artem.bank.entity.Result;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ResultTest {
    @Test
    void successHoldsValue() {
        Result<String> result = Result.success("done");

        assertTrue(result.isSuccess());
        assertEquals("done", result.getValue());
        assertNull(result.getError());
    }

    @Test
    void successWithNullValueIsStillSuccess() {
        Result<String> result = Result.success(null);

        assertTrue(result.isSuccess());
        assertNull(result.getValue());
    }

    @Test
    void failureHoldsError() {
        Result<String> result = Result.failure("something went wrong");

        assertFalse(result.isSuccess());
        assertEquals("something went wrong", result.getError());
    }

    @Test
    void failureWithNullErrorThrows() {
        assertThrows(NullPointerException.class, () -> Result.failure(null));
    }

    @Test
    void getValueOnFailureThrows() {
        Result<String> result = Result.failure("something went wrong");

        IllegalStateException e = assertThrows(IllegalStateException.class, result::getValue);

        assertTrue(e.getMessage().contains("something went wrong"));
    }
}
