package com.bankslipsrest.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BankSlipsPayDTO{

    @JsonProperty("payment_date")
    @NotNull
    private LocalDate paymentDate ;
}