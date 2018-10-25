package com.bankslipsrest.dto;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.bankslipsrest.entity.BankSlipsPaymentStatus;

import lombok.Data;

@Data
public class BankSlipsCancelDTO{
    @NotNull
    private UUID id;
    @NotNull
    private BankSlipsPaymentStatus status;
}