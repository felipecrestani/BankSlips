package com.bankslipsrest.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.bankslipsrest.exception.ApiException;
import com.bankslipsrest.model.BankSlips;
import com.bankslipsrest.model.BankSlipsDetailsDTO;
import com.bankslipsrest.model.BankSlipsPaymentStatus;
import com.bankslipsrest.model.BankSlipsPostDTO;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:test.properties") 
public class BankSlipsServiceTests {
	
	@Autowired
	private BankSlipsService bankSlipsService;

	private BankSlips bankslip = null;	
	private BankSlipsPostDTO bankSlipsPostDTO = null;

	@Before
	public void setUp() {
		bankSlipsPostDTO = new BankSlipsPostDTO();
		bankSlipsPostDTO.setCustomer("Dents Company S.A");
		bankSlipsPostDTO.setDueDate(new Date(2018,10,1));
		bankSlipsPostDTO.setTotalInCents(BigDecimal.valueOf(1000));
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
		this.bankslip = this.bankSlipsService.createBankSlips(this.bankSlipsPostDTO);
		assertBankSlips(bankslip,bankslip.getId(), bankslip.getTotalInCents(), bankslip.getDueDate(), bankslip.getStatus());				
	}

	private void assertBankSlips(BankSlips saved, UUID id, BigDecimal totalInCents, Date dueDate, BankSlipsPaymentStatus status) {
		assertThat(id.toString().length() == 36);
		assertThat(saved.getCustomer()).isEqualTo("Dents Company S.A");
		assertThat(saved.getTotalInCents()).isEqualTo(totalInCents);
		assertThat(saved.getDueDate()).isEqualTo(dueDate);
		assertThat(saved.getStatus()).isEqualTo(status);
	}
}
