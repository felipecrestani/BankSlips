package com.bankslipsrest.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.bankslipsrest.entity.BankSlipsPaymentStatus;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class BankSlipsDetailsDTO{

    private UUID id;
    @DateTimeFormat(pattern = "yyyy-dd-mm")
    private LocalDate dueDate;
    @DateTimeFormat(pattern = "yyyy-dd-mm")
    private LocalDate payment_date;
    private BigDecimal totalInCents;
    private String customer;
    private BigDecimal fine;
    private BankSlipsPaymentStatus status;

}