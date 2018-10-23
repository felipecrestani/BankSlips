package com.bankslipsrest.exception;

import lombok.Getter;

@Getter
public class ApiAlertException extends ApiException {

    public ApiAlertException(String message, String errorKey) {
        super(message, errorKey);
    }

}
