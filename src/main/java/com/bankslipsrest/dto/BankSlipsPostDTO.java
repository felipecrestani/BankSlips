package com.bankslipsrest.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BankSlipsPostDTO{
    @NotNull
    @JsonProperty("due_date")
    private LocalDate dueDate;
    @NotNull
    @JsonProperty("total_in_cents")
    private BigDecimal totalInCents;
    @NotNull
    @JsonProperty("customer")
    private String customer;
}