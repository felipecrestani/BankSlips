package com.bankslipsrest.service;

import com.bankslipsrest.model.BankSlips;
import com.bankslipsrest.model.BankSlipsPostDTO;
import com.bankslipsrest.repository.BankSlipsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankSlipsService{

    @Autowired
    private BankSlipsRepository repository;
    
    public String test(){
        return "Test";
    }

    public BankSlips createBankSlips(BankSlipsPostDTO dto){
        BankSlips entity = new BankSlips();
        entity.setDueDate(dto.getDueDate());
        entity.setTotalInCents(dto.getTotalInCents());
        entity.setCustomer(dto.getCustomer());

        return repository.save(entity);
    }
}