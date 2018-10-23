package com.bankslipsrest.controller;

import java.util.UUID;

import com.bankslipsrest.DemoApplicationTests;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class BankSlipsControllerTests extends DemoApplicationTests {

	private MockMvc mockMvc;
	
	@Autowired
	private BankSlipsController bankSlipsController;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(bankSlipsController).build();
	}

	@Test
	public void testPOSTBankSlips() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/rest/bankslips")
		.param("due_date", "2018-10-01")
		.param("total_in_cents", "1000")
		.param("customer", "Test Company SA"))
		.andExpect(MockMvcResultMatchers.status().isNoContent());
	}

	/*@Test
	public void testGETBankSlipsByIdNotFound() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/rest/bankslips/{id}")
		.param("id", UUID.randomUUID().toString()))
		.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	public void testDELETEBankSlipsById() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/rest/bankslips/{id}"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}*/

}
