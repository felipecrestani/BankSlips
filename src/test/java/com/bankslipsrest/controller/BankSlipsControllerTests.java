package com.bankslipsrest.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.bankslipsrest.model.BankSlips;
import com.bankslipsrest.model.BankSlipsPostDTO;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
		bankSlipsPostDTO.setDueDate(new Date(2018,10,1));
		bankSlipsPostDTO.setTotalInCents(BigDecimal.valueOf(1000));
	}

	@Test
	public void testPOSTBankSlips() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/rest/bankslips").contentType(MediaType.APPLICATION_JSON)
		.content("{\"due_date\":\"2018-10-01\",\"total_in_cents\":\"1000\",\"customer\":\"Dents Company S.A\"}"))
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
	public void testGETBankSlipsById() throws Exception {
		BankSlips bs = bankSlipsController.createBankSlips(bankSlipsPostDTO).getBody();
		this.bankslip = bs;		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/rest/bankslips/{id}/", bankslip.getId()))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
