package com.artem.bank.model;

import java.util.Objects;

public final class Result<T> {
    private final T value;
    private final String error;


    private Result(T value, String error) {
        this.value = value;
        this.error = error;
    }

    public static <T> Result<T> success(T value) {
        return new Result<>(value, null);
    }

    public static <T> Result<T> failure(String error) {
        Objects.requireNonNull(error, "error");
        return new Result<>(null, error);
    }

    public boolean isSuccess() {
        return this.error == null;
    }

    public T getValue() {
        if (!isSuccess()) {
            throw new IllegalStateException("Result is failure: " + error);
        }
        return value;
    }

    public String getError() {
        return this.error;
    }
}
