package com.bankslipsrest.service;

import java.util.Optional;
import java.util.UUID;

import com.bankslipsrest.model.BankSlips;
import com.bankslipsrest.model.BankSlipsPostDTO;
import com.bankslipsrest.model.BankSlipsPutDTO;
import com.bankslipsrest.repository.BankSlipsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankSlipsService{

    @Autowired
    private BankSlipsRepository repository;

    public Optional<BankSlips> getById(UUID id){
        return repository.findById(id);
    }

    public void deleteById(UUID id){
        repository.deleteById(id);
    }

    public BankSlips createBankSlips(BankSlipsPostDTO dto){
        BankSlips entity = new BankSlips();
        entity.setDueDate(dto.getDueDate());
        entity.setTotalInCents(dto.getTotalInCents());
        entity.setCustomer(dto.getCustomer());

        return repository.save(entity);
    }

    public BankSlips cancelBankSlips(BankSlipsPutDTO dto)
    {       
        BankSlips entity = repository.getOne(dto.getId());        
        entity.setStatus(dto.getStatus());
        return repository.saveAndFlush(entity);
    }
}