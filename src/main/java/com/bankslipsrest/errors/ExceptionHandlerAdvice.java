package com.bankslipsrest.errors;

import java.nio.file.AccessDeniedException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.NoResultException;

import com.bankslipsrest.exception.ApiException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleAnyException(final Exception exception, final WebRequest request) {
        log.error(exception.getMessage(), exception);
        return getDefaultErrorResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NoResultException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleNoResultException(final Exception exception,
        final WebRequest request) {
        log.error(exception.getMessage(), exception);
        return getDefaultErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleAccessControlException(final Exception exception,
        final WebRequest request) {
        log.error(exception.getMessage(), exception);
        return getDefaultErrorResponse(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = ApiException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleNgAlertException(final ApiException exception,
        final WebRequest request) {
        log.error(exception.getMessage(), exception);
        return getNGAlertMessage(exception.getMessage(), exception.getErrorKey(), HttpStatus.UNAUTHORIZED);
    }

    private ResponseEntity<Map<String, Object>> getNGAlertMessage(final String message, String errorKey,
        final HttpStatus httpStatus) {
        final Map<String, Object> errorMap = getMessageErrorMap(message, errorKey, httpStatus);
        return new ResponseEntity<>(errorMap, httpStatus);
    }

    private Map<String, Object> getMessageErrorMap(String message, String errorKey, HttpStatus httpStatus) {
        final Map<String, Object> map = new LinkedHashMap<>();
        map.put("timestamp", System.currentTimeMillis());
        map.put("status", httpStatus.value());
        map.put("message", message);
        map.put("errorKey", errorKey);
        return map;
    }

    private ResponseEntity<Map<String, Object>> getDefaultErrorResponse(final String message,
        final HttpStatus httpStatus) {
        final Map<String, Object> errorMap = getMessageErrorMap(message, "label_error_" + httpStatus.value(), httpStatus);
        return new ResponseEntity<>(errorMap, httpStatus);
    }

}