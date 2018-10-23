package com.bankslipsrest.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BankSlipsPostDTO{
    @NotNull
    @JsonProperty("due_date")
    private Date dueDate;
    @NotNull
    @JsonProperty("total_in_cents")
    private BigDecimal totalInCents;
    @NotNull
    @JsonProperty("customer")
    private String customer;
}