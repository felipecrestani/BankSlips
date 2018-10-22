package com.bankslipsrest.model;

import java.math.BigDecimal;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BankSlipsPostDTO{
    @JsonProperty("due_date")
    private Date dueDate;
    @JsonProperty("total_in_cents")
    private BigDecimal totalInCents;
    @JsonProperty("customer")
    private String customer;
}