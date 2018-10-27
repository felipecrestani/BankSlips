package com.bankslipsrest.service;

import com.bankslipsrest.dto.BankSlipsDTO;
import com.bankslipsrest.dto.BankSlipsDetailsDTO;
import com.bankslipsrest.dto.BankSlipsPayDTO;
import com.bankslipsrest.dto.BankSlipsPostDTO;
import com.bankslipsrest.entity.BankSlips;
import com.bankslipsrest.entity.BankSlipsPaymentStatus;
import com.bankslipsrest.repository.BankSlipsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BankSlipsService{

    @Autowired
    private BankSlipsRepository repository;

    public BankSlipsDetailsDTO getById(UUID id){
        BankSlips entity = repository.findById(id).orElseThrow(() -> new NoResultException("Bankslip not found with the specified id"));

        BankSlipsDetailsDTO dto = getBankSlipsDetailsDTO(entity);
        return dto;
    }

    public List<BankSlipsDTO> getAll(){

        return repository.findAll().stream()
                .map(BankSlips -> getBankSlipsDTO(BankSlips))
                .collect(Collectors.toList());
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Transactional
    public BankSlipsDTO createBankSlips(BankSlipsPostDTO dto){
        BankSlips entity = getBankSlips(dto);
        return getBankSlipsDTO(repository.save(entity));
    }

    private BankSlips getBankSlips(BankSlipsPostDTO dto) {
        BankSlips entity = new BankSlips();
        entity.setDueDate(LocalDate.parse(dto.getDueDate()));
        entity.setTotalInCents(dto.getTotalInCents());
        entity.setCustomer(dto.getCustomer());
        entity.setStatus(BankSlipsPaymentStatus.PENDING);
        return entity;
    }

    public BankSlips cancelBankSlips(UUID id)
    {       
        BankSlips entity = repository.findById(id).orElseThrow(() -> new NoResultException("Bankslip not found with the specified id"));
        entity.setStatus(BankSlipsPaymentStatus.CANCELED);
        return repository.saveAndFlush(entity);
    }

    public BankSlips payBankSlips(UUID id, BankSlipsPayDTO dto)
    {       
        BankSlips entity = repository.findById(id).orElseThrow(() -> new NoResultException("Bankslip not found with the specified id"));
        entity.setStatus(BankSlipsPaymentStatus.PAID);    
        entity.setDueDate(LocalDate.parse(dto.getPaymentDate()));
        return repository.saveAndFlush(entity);
    }

    private BankSlipsDetailsDTO getBankSlipsDetailsDTO(BankSlips entity) {
        BankSlipsDetailsDTO dto = new BankSlipsDetailsDTO();
        dto.setId(entity.getId().toString());
        dto.setDueDate(entity.getDueDate().toString());
        dto.setTotalInCents(entity.getTotalInCents());
        dto.setCustomer(entity.getCustomer());
        dto.setStatus(entity.getStatus().name());
        dto.setFine(entity.getFine());
        return dto;
    }

    private BankSlipsDTO getBankSlipsDTO(BankSlips entity) {
        BankSlipsDTO bankSlipsDTO = new BankSlipsDTO();
        bankSlipsDTO.setId(entity.getId().toString());
        bankSlipsDTO.setDueDate(entity.getDueDate().toString());
        bankSlipsDTO.setTotalInCents(entity.getTotalInCents());
        bankSlipsDTO.setCustomer(entity.getCustomer());
        bankSlipsDTO.setStatus(entity.getStatus().name());
        return bankSlipsDTO;
    }

}