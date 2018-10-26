package com.bankslipsrest.controller;

import java.util.List;
import java.util.UUID;

import com.bankslipsrest.dto.BankSlipsCancelDTO;
import com.bankslipsrest.dto.BankSlipsDetailsDTO;
import com.bankslipsrest.dto.BankSlipsPayDTO;
import com.bankslipsrest.dto.BankSlipsPostDTO;
import com.bankslipsrest.entity.BankSlips;
import com.bankslipsrest.exception.ApiException;
import com.bankslipsrest.service.BankSlipsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("Bankslips")
@RestController
@RequestMapping("rest/bankslips")
public class BankSlipsController{
  
    @Autowired
    private BankSlipsService service;

    @ApiOperation(value = "Get Bank Slips by Id)")
	@ApiResponses(value = { 
            @ApiResponse(code = 204, message = "No content"), 
            @ApiResponse(code = 404, message = "Bankslip not found with the specified id") 
	})
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<BankSlipsDetailsDTO> getById(@PathVariable UUID id) throws ApiException{
        BankSlipsDetailsDTO response =  service.getById(id);
        return new ResponseEntity<BankSlipsDetailsDTO>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna uma lista de boletos)")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK") 
	})
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<BankSlips>> get() throws ApiException{
		List<BankSlips> bankSlipsPage = service.getAll();
		return ResponseEntity.ok(bankSlipsPage);
	}
    
    @ApiOperation(value = "Create a Bank Slips")
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "Bankslip created"), 
			@ApiResponse(code = 400, message = "Bankslip not provided in the request body"), 
			@ApiResponse(code = 422, message = "Invalid bankslip provided.The possible reasons are: A field of the provided bankslip was null or with invalid values")
	})
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<BankSlips> createBankSlips(@RequestBody BankSlipsPostDTO dto) throws ApiException{
        BankSlips entity = service.createBankSlips(dto);
        return new ResponseEntity<BankSlips>(entity,HttpStatus.CREATED);
    }

    @ApiOperation(value = "Cancel a BankSlips")
	@ApiResponses(value = { 
            @ApiResponse(code = 204, message = "Bankslip canceled"), 
            @ApiResponse(code = 404, message = "Bankslip not found with the specified i") 
	})
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<BankSlips> cancelBankSlips(@PathVariable UUID id) throws ApiException{
        BankSlips bankSlips = service.cancelBankSlips(id);
        return new ResponseEntity<BankSlips>(bankSlips,HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Pay a BankSlips")
	@ApiResponses(value = { 
            @ApiResponse(code = 204, message = "No content"), 
            @ApiResponse(code = 404, message = "Bankslip not found with the specified id") 
	})
    @PostMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<BankSlips> payBankSlips(@PathVariable UUID id, @RequestBody BankSlipsPayDTO dto ) throws ApiException{
        BankSlips bankSlips = service.payBankSlips(id, dto);
        return new ResponseEntity<BankSlips>(bankSlips,HttpStatus.NO_CONTENT);
    }

}