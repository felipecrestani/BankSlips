package com.bankslipsrest.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.apache.tomcat.jni.Local;
import org.junit.Test;

public class BankSlipsFineTest {

	private LocalDate testDate =  LocalDate.of(2018, 1, 1);

	@Test
	public void whenIsNotOverdue_thenReturnZero() {
		BigDecimal fine = BankSlipsFine.of(new BigDecimal("99000"), testDate, testDate);
		assertThat(fine).isEqualByComparingTo(BigDecimal.ZERO);
	}

	@Test
	public void whenIsAdvancedPayment_thenReturnZero() {
		BigDecimal fine = BankSlipsFine.of(new BigDecimal("99000"), testDate, testDate.minusMonths(1));
		assertThat(fine).isEqualByComparingTo(BigDecimal.ZERO);
	}

	@Test
	public void whenIsLatePayment_thenFine() {
		BigDecimal fine = BankSlipsFine.of(new BigDecimal("50000"), testDate, testDate.plusDays(5));
		assertThat(fine).isEqualByComparingTo(new BigDecimal("1250"));
	}

	@Test
	public void whenIsLatePaymentTenDays_thenFine() {
		BigDecimal fine = BankSlipsFine.of(new BigDecimal("50000"), testDate, testDate.plusDays(10));
		assertThat(fine).isEqualByComparingTo(new BigDecimal("2500"));
	}

	@Test
	public void whenIsLatePaymentFivetenDays_thenFine() {
		BigDecimal fine = BankSlipsFine.of(new BigDecimal("50000"), testDate, testDate.plusDays(15));
		assertThat(fine).isEqualByComparingTo(new BigDecimal("7500"));
	}

}