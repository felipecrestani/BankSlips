package com.bankslipsrest.controller;

import com.bankslipsrest.DemoApplicationTests;
import com.bankslipsrest.dto.BankSlipsDTO;
import com.bankslipsrest.dto.BankSlipsPostDTO;
import com.bankslipsrest.entity.BankSlipsPaymentStatus;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class BankSlipsControllerTests extends DemoApplicationTests {

	private MockMvc mockMvc;	
	@Autowired
	private BankSlipsController bankSlipsController;

	private BankSlipsDTO bankSlipsDTO = null;
	private BankSlipsPostDTO bankSlipsPostDTO = null;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(bankSlipsController).build();

		bankSlipsPostDTO = new BankSlipsPostDTO();
		bankSlipsPostDTO.setCustomer("Test Company S.A");
		bankSlipsPostDTO.setDueDate("2018-10-01");
		bankSlipsPostDTO.setTotalInCents(BigDecimal.valueOf(1000));
	}

	@Test
	public void testPOSTBankSlips() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/rest/bankslips").contentType(MediaType.APPLICATION_JSON_UTF8)
		.content("{\"due_date\":\"2018-10-01\",\"total_in_cents\":\"1000\",\"customer\":\"Test Company S.A\"}"))
		.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	public void testDELETEBankSlips() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/rest/bankslips").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"due_date\":\"2018-10-01\",\"total_in_cents\":\"1000\",\"customer\":\"Test Company S.A\"}"))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	@Transactional
	public void testGETBankSlipsByIdNotFound() throws Exception {
		try {
			this.mockMvc.perform(MockMvcRequestBuilders.get("/rest/bankslips/{id}/", UUID.randomUUID()))
			.andExpect(MockMvcResultMatchers.status().isNotFound());
		} catch (Exception e) {
			assertThat(e.getCause()).hasMessage("Bankslip not found with the specified id");
		}
	}

	@Test
	@Transactional
	public void testGETAllBankSlips() throws Exception {
			this.mockMvc.perform(MockMvcRequestBuilders.get("/rest/bankslips"))
					.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	@Transactional
	public void testGETBankSlipsById() throws Exception {
		BankSlipsDTO bs = bankSlipsController.createBankSlips(bankSlipsPostDTO).getBody();
		this.bankSlipsDTO = bs;
		this.mockMvc.perform(MockMvcRequestBuilders.get("/rest/bankslips/{id}/", bankSlipsDTO.getId()))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	@Transactional
	public void testDELETEBankSlipsById() throws Exception {
		BankSlipsDTO bs = bankSlipsController.createBankSlips(bankSlipsPostDTO).getBody();
		this.bankSlipsDTO = bs;
		ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.delete("/rest/bankslips/{id}/", bankSlipsDTO.getId() ))
		.andExpect(MockMvcResultMatchers.status().isNoContent());
		result.andExpect(jsonPath("$.status", is(BankSlipsPaymentStatus.CANCELED.toString())));
	}

	@Test
	@Transactional
	public void testPayBankSlipsById() throws Exception {
		BankSlipsDTO bs = bankSlipsController.createBankSlips(bankSlipsPostDTO).getBody();
		this.bankSlipsDTO = bs;
		ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.post("/rest/bankslips/{id}/payments", bankSlipsDTO.getId() )
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"payment_date\":\"2018-10-01\"}"))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
		result.andExpect(jsonPath("$.status", is(BankSlipsPaymentStatus.PAID.toString())));
	}
}
