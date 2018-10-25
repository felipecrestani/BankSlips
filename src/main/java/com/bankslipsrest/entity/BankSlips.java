package com.bankslipsrest.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class BankSlips{

    @Id @GeneratedValue()
    private UUID id;

    @Column(nullable = false)
	  @NotNull
    private LocalDate dueDate;

    private LocalDate paymentDate;

    @Column(nullable = false, scale = 16, precision = 0)
    @NotNull
    private BigDecimal totalInCents;

    @Column 
    private String customer;
    
    @Column
    @NotNull
    private BankSlipsPaymentStatus status;

    public BigDecimal getFine() {
		  return BankSlipsFine.of(this.totalInCents, this.dueDate, Optional.ofNullable(paymentDate).orElse(LocalDate.now()));
	}

}