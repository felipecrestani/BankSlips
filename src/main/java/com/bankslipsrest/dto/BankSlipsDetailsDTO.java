package com.bankslipsrest.dto;

import com.bankslipsrest.entity.BankSlipsPaymentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class BankSlipsDetailsDTO extends BankSlipsDTO{
    private BigDecimal fine;
}