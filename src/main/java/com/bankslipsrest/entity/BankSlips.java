package com.bankslipsrest.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
public class BankSlips{

    @Id @GeneratedValue()
    private UUID id;

    @Column
    @DateTimeFormat(pattern = "yyyy-dd-mm")
    private LocalDate dueDate;

    @DateTimeFormat(pattern = "yyyy-dd-mm")
    private LocalDate paymentDate;

    @Column(nullable = false, scale = 16, precision = 0)
    private BigDecimal totalInCents;

    @Column 
    private String customer;
    
    @Column
    private BankSlipsPaymentStatus status;

    public BigDecimal getFine() {
		return BankSlipsFine.of(this.totalInCents, this.dueDate, Optional.ofNullable(paymentDate).orElse(LocalDate.now()));
	}

}