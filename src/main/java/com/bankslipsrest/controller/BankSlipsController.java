package com.bankslipsrest.controller;

import java.util.UUID;

import com.bankslipsrest.model.BankSlips;
import com.bankslipsrest.model.BankSlipsCancelDTO;
import com.bankslipsrest.model.BankSlipsDetailsDTO;
import com.bankslipsrest.model.BankSlipsPayDTO;
import com.bankslipsrest.model.BankSlipsPostDTO;
import com.bankslipsrest.service.BankSlipsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("Bankslips")
@RestController
@RequestMapping("rest")
public class BankSlipsController{
  
    @Autowired
    private BankSlipsService service;

    
    @ApiOperation(value = "Find Bank Slips by Id)")
	@ApiResponses(value = { 
            @ApiResponse(code = 204, message = "No content"), 
            @ApiResponse(code = 404, message = "Bankslip not found with the specified id") 
	})
    @GetMapping(value =  "bankslips/{id}/payments" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<BankSlipsDetailsDTO> getById(@PathVariable UUID id){
        BankSlipsDetailsDTO response =  service.getById(id);

        if(response == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        return new ResponseEntity<BankSlipsDetailsDTO>(response, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("bankslips/{id}")
    public void deleteById(@PathVariable UUID id){
        service.deleteById(id);
    }
    
    @ApiOperation(value = "Create a Bank Slips")
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "Bankslip created"), 
			@ApiResponse(code = 400, message = "Bankslip not provided in the request body"), 
			@ApiResponse(code = 422, message = "Invalid bankslip provided.The possible reasons are: A field of the provided bankslip was null or with invalid values")
	})
    @PostMapping(value = "bankslips", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<BankSlips> createBankSlips(@RequestBody BankSlipsPostDTO dto){
        BankSlips entity = service.createBankSlips(dto);
        return new ResponseEntity<BankSlips>(entity,HttpStatus.CREATED);
    }

    @ApiOperation(value = "Cancel a BankSlips")
	@ApiResponses(value = { 
            @ApiResponse(code = 204, message = "Bankslip canceled"), 
            @ApiResponse(code = 404, message = "Bankslip not found with the specified i") 
	})
    @PutMapping(value = "bankslips", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<BankSlips> cancelBankSlips(@RequestBody BankSlipsCancelDTO dto ){
        BankSlips bankSlips = service.cancelBankSlips(dto);

        if(bankSlips == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        return new ResponseEntity<BankSlips>(bankSlips,HttpStatus.CREATED);
    }

    @ApiOperation(value = "Pay a BankSlips")
	@ApiResponses(value = { 
            @ApiResponse(code = 204, message = "No content"), 
            @ApiResponse(code = 404, message = "Bankslip not found with the specified id") 
	})
    @PostMapping(value = "bankslips/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE))
    public ResponseEntity<BankSlips> payBankSlips(@PathVariable UUID id, @RequestBody BankSlipsPayDTO dto ){
        BankSlips bankSlips = service.payBankSlips(id, dto);

        if(bankSlips == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        return new ResponseEntity<BankSlips>(bankSlips,HttpStatus.NO_CONTENT);
    }

}