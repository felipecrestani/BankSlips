package com.bankslipsrest.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

import com.bankslipsrest.model.BankSlips;
import com.bankslipsrest.model.BankSlipsPostDTO;

import org.hibernate.Hibernate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:test.properties") 
@Transactional
public class BankSlipsControllerTests {

	private MockMvc mockMvc;
	
	@Autowired
	private BankSlipsController bankSlipsController;

	private BankSlips bankslip = null;	
	private BankSlipsPostDTO bankSlipsPostDTO = null;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(bankSlipsController).build();

		bankSlipsPostDTO = new BankSlipsPostDTO();
		bankSlipsPostDTO.setCustomer("Dents Company S.A");
		bankSlipsPostDTO.setDueDate(Date.valueOf("2018-10-01"));
		bankSlipsPostDTO.setTotalInCents(BigDecimal.valueOf(1000));
	}

	@Test
	public void testPOSTBankSlips() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/rest/bankslips").contentType(MediaType.APPLICATION_JSON)
		.content("{\"due_date\":\"2018-10-01\",\"total_in_cents\":\"1000\",\"customer\":\"Dents Company S.A\"}"))
		.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	// @Test
	// public void testGETBankSlipsByIdNotFound() throws Exception {
	// 	this.mockMvc.perform(MockMvcRequestBuilders.get("/rest/bankslips/{id}", UUID.randomUUID().toString()))
	// 	.andExpect(MockMvcResultMatchers.status().isNotFound());
	// }

	@Test
	public void testGETBankSlipsById() throws Exception {
		BankSlips bs = bankSlipsController.createBankSlips(bankSlipsPostDTO).getBody();
		this.bankslip = bs;		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/rest/bankslips/{id}", bankslip.getId()))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
