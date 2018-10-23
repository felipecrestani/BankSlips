package com.bankslipsrest.model;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class BankSlipsCancelDTO{
    @NotNull
    private UUID id;
    @NotNull
    private BankSlipsPaymentStatus status;
}