package com.bankslipsrest.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.bankslipsrest.DemoApplicationTests;
import com.bankslipsrest.dto.BankSlipsDTO;
import com.bankslipsrest.dto.BankSlipsPayDTO;
import com.bankslipsrest.dto.BankSlipsPostDTO;
import com.bankslipsrest.entity.BankSlips;
import com.bankslipsrest.entity.BankSlipsPaymentStatus;
import com.bankslipsrest.exception.ApiException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class BankSlipsServiceTests extends DemoApplicationTests {
	
	@Autowired
	private BankSlipsService bankSlipsService;

	private BankSlips bankSlips = null;
	private BankSlipsDTO bankSlipsDTO = null;
	private BankSlipsPostDTO bankSlipsPostDTO = null;
	private BankSlipsPayDTO bankSlipsPayDTO = null;

	@Before
	public void setUp() {
		bankSlipsPostDTO = new BankSlipsPostDTO();
		bankSlipsPostDTO.setCustomer("Test Company S.A");
		bankSlipsPostDTO.setDueDate("2018-10-01");
		bankSlipsPostDTO.setTotalInCents(BigDecimal.valueOf(1000));

		bankSlipsPayDTO = new BankSlipsPayDTO();
		bankSlipsPayDTO.setPaymentDate("2018-10-01");

		this.bankSlipsService.createBankSlips(this.bankSlipsPostDTO);
	}

	@Test
	@Transactional
	public void BankSlipsServiceGetById() throws ApiException {
		try {
			this.bankSlipsService.getById(UUID.randomUUID());
		} catch (Exception e) {
			assertThat(e).hasMessage("Bankslip not found with the specified id");
		}				
	}

	@Test
	@Transactional
	public void BankSlipsServiceCreate() throws Exception {
		this.bankSlipsDTO = this.bankSlipsService.createBankSlips(this.bankSlipsPostDTO);
		assertBankSlips(bankSlipsDTO,bankSlipsDTO.getId(), bankSlipsDTO.getTotalInCents(), bankSlipsDTO.getDueDate(), bankSlipsDTO.getStatus());
	}

	@Test
	@Transactional
	public void BankSlipsServicePay() throws Exception {
		this.bankSlipsDTO = this.bankSlipsService.createBankSlips(this.bankSlipsPostDTO);
		this.bankSlips = this.bankSlipsService.payBankSlips(UUID.fromString(this.bankSlipsDTO.getId()), this.bankSlipsPayDTO);
		assertThat(bankSlips.getStatus().equals(BankSlipsPaymentStatus.PAID));
	}

	@Test
	@Transactional
	public void BankSlipsServiceDelete() throws Exception {
		this.bankSlipsDTO = this.bankSlipsService.createBankSlips(this.bankSlipsPostDTO);
		this.bankSlips = this.bankSlipsService.cancelBankSlips(UUID.fromString(this.bankSlipsDTO.getId()));
		assertThat(bankSlips.getStatus().equals(BankSlipsPaymentStatus.CANCELED));
	}

	@Test
	@Transactional
	public void BankSlipsServiceGetAll() throws Exception {
		this.bankSlipsService.createBankSlips(this.bankSlipsPostDTO);
		this.bankSlipsService.createBankSlips(this.bankSlipsPostDTO);
		List<BankSlipsDTO> list = this.bankSlipsService.getAll();
		assertThat(list.size() == 2);
	}

	private void assertBankSlips(BankSlipsDTO saved, String id, BigDecimal totalInCents, String dueDate, String status) {
		assertThat(id.length() == 36);
		assertThat(saved.getCustomer()).isEqualTo("Test Company S.A");
		assertThat(saved.getTotalInCents()).isEqualTo(totalInCents);
		assertThat(saved.getDueDate()).isEqualTo(dueDate);
		assertThat(saved.getStatus()).isEqualTo(status);
	}
}
