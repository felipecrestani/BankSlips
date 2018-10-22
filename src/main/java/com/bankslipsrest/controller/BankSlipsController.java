package com.bankslipsrest.controller;

import com.bankslipsrest.model.BankSlips;
import com.bankslipsrest.model.BankSlipsPostDTO;
import com.bankslipsrest.service.BankSlipsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest")
public class BankSlipsController{
  
    @Autowired
    private BankSlipsService service;

    @GetMapping("test")
    public String testesNaoToue(){
        return service.test();

    }

    @PostMapping("bankslips")
    public BankSlips createBankSlips(@RequestBody BankSlipsPostDTO dto){
        return service.createBankSlips(dto);
    }

}