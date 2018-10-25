package com.bankslipsrest.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import com.bankslipsrest.dto.BankSlipsCancelDTO;
import com.bankslipsrest.dto.BankSlipsDetailsDTO;
import com.bankslipsrest.dto.BankSlipsPayDTO;
import com.bankslipsrest.dto.BankSlipsPostDTO;
import com.bankslipsrest.entity.BankSlips;
import com.bankslipsrest.entity.BankSlipsPaymentStatus;
import com.bankslipsrest.repository.BankSlipsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankSlipsService{

    @Autowired
    private BankSlipsRepository repository;

    public BankSlipsDetailsDTO getById(UUID id){
        BankSlips entity = repository.findById(id).orElseThrow(() -> new NoResultException("Bankslip not found with the specified id"));

        BankSlipsDetailsDTO dto = new BankSlipsDetailsDTO();
        dto.setId(entity.getId());
        dto.setDueDate(entity.getDueDate());
        dto.setPayment_date(entity.getPaymentDate());
        dto.setTotalInCents(entity.getTotalInCents());
        dto.setCustomer(entity.getCustomer());
        dto.setStatus(entity.getStatus());
        dto.setFine(entity.getFine());
        return dto;
    }

    public List<BankSlips> getAll(){
        return repository.findAll();
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Transactional
    public BankSlips createBankSlips(BankSlipsPostDTO dto){
        BankSlips entity = new BankSlips();
        entity.setDueDate(dto.getDueDate());
        entity.setTotalInCents(dto.getTotalInCents());
        entity.setCustomer(dto.getCustomer());
        entity.setStatus(BankSlipsPaymentStatus.PENDING);

        return repository.save(entity);
    }

    public BankSlips cancelBankSlips(BankSlipsCancelDTO dto)
    {       
        BankSlips entity = repository.findById(dto.getId()).orElseThrow(() -> new NoResultException("Bankslip not found with the specified id"));        
        entity.setStatus(BankSlipsPaymentStatus.CANCELED);
        return repository.saveAndFlush(entity);
    }

    public BankSlips payBankSlips(UUID id, BankSlipsPayDTO dto)
    {       
        BankSlips entity = repository.findById(id).orElseThrow(() -> new NoResultException("Bankslip not found with the specified id"));
        entity.setStatus(BankSlipsPaymentStatus.PAID);    
        entity.setDueDate(dto.getPaymentDate());
        return repository.saveAndFlush(entity);
    }
}