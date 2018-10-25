package com.bankslipsrest.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;

public class BankSlipsFineTest {

	@Test
	public void givenFineParam_whenBankSlipNaoEstaVencido_thenReturnZero() {
		BigDecimal fine = BankSlipsFine.of(new BigDecimal("99000"), LocalDate.of(2018, 1, 1), LocalDate.of(2018, 1, 1));
		assertThat(fine).isEqualByComparingTo(BigDecimal.ZERO);
	}

	@Test
	public void givenParam_whenBankslipIsAdiantadoMaisDe10Dias_thenReturnZero() {
		BigDecimal fine = BankSlipsFine.of(new BigDecimal("99000"), LocalDate.of(2018, 1, 1), LocalDate.of(2018, 1, 1).minusDays(10));
		assertThat(fine).isEqualByComparingTo(BigDecimal.ZERO);
	}

	@Test
	public void givenParam_whenIsDue_thenFine() {
		LocalDate dueDate = LocalDate.of(2018, 1, 1);
		BigDecimal fine = BankSlipsFine.of(new BigDecimal("50000"), dueDate, dueDate.plusDays(5));
		assertThat(fine).isEqualByComparingTo(new BigDecimal("1250"));
	}

	@Test
	public void givenParam_whenIsDue10days_thenFine() {
		LocalDate dueDate = LocalDate.of(2018, 1, 1);
		BigDecimal fine = BankSlipsFine.of(new BigDecimal("50000"), dueDate, dueDate.plusDays(10));
		assertThat(fine).isEqualByComparingTo(new BigDecimal("2500"));
	}

	@Test
	public void givenParam_whenIsDue15Days_thenFine() {
		LocalDate dueDate = LocalDate.of(2018, 1, 1);
		BigDecimal fine = BankSlipsFine.of(new BigDecimal("50000"), dueDate, dueDate.plusDays(15));
		assertThat(fine).isEqualByComparingTo(new BigDecimal("7500"));
	}

}