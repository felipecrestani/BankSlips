package com.bankslipsrest.controller;

import java.util.UUID;

import com.bankslipsrest.model.BankSlips;
import com.bankslipsrest.model.BankSlipsCancelDTO;
import com.bankslipsrest.model.BankSlipsPayDTO;
import com.bankslipsrest.model.BankSlipsPostDTO;
import com.bankslipsrest.service.BankSlipsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest")
public class BankSlipsController{
  
    @Autowired
    private BankSlipsService service;

    @GetMapping("bankslips/{id}")
    public ResponseEntity<BankSlips> getById(@PathVariable UUID id){
        return service.getById(id).isPresent() ? new ResponseEntity<>(service.getById(id).get(), HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("bankslips/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteById(@PathVariable UUID id){
        service.deleteById(id);
    }
    
    @PostMapping("bankslips")
    public ResponseEntity<BankSlips> createBankSlips(@RequestBody BankSlipsPostDTO dto){
        BankSlips entity = service.createBankSlips(dto);
            return new ResponseEntity<BankSlips>(entity,HttpStatus.CREATED);
    }

    @PutMapping("bankslips")
    public BankSlips cancelBankSlips(@RequestBody BankSlipsCancelDTO dto ){
        return service.cancelBankSlips(dto);
    }

    @PostMapping("bankslips/{id}")
    public BankSlips payBankSlips(@PathVariable UUID id, @RequestBody BankSlipsPayDTO dto ){
        return service.payBankSlips(id, dto);
    }

}