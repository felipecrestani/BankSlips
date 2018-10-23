package com.bankslipsrest.exception;

import lombok.Getter;

@Getter
public abstract class ApiException extends Exception {

    private final String errorKey;

    protected ApiException(String message, String errorKey) {
        super(message);
        this.errorKey = errorKey;
    }
}
