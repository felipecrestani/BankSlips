package com.bankslipsrest.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankSlips{

    @Id @GeneratedValue()
    private UUID id;

    @Column(nullable = false)
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;

    @Column(nullable = false, scale = 16, precision = 0)
    @NotNull
    private BigDecimal totalInCents;

    @Column(nullable = false)
    private String customer;

    @Column(nullable = false)
    @NotNull
    private BankSlipsPaymentStatus status;

    public BigDecimal getFine() {
		  return BankSlipsFine.of(this.totalInCents, this.dueDate, Optional.ofNullable(paymentDate).orElse(LocalDate.now()));
	}

}