package com.bankslipsrest.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class BankSlipsDetailsDTO{

    private UUID id;
    @DateTimeFormat(pattern = "yyyy-dd-mm")
    private Date dueDate;
    @DateTimeFormat(pattern = "yyyy-dd-mm")
    private Date payment_date;
    private BigDecimal totalInCents;
    private String customer;
    private BigDecimal fine;
    private BankSlipsPaymentStatus status;

}