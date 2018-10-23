package com.bankslipsrest.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import com.bankslipsrest.model.BankSlips;
import com.bankslipsrest.model.BankSlipsCancelDTO;
import com.bankslipsrest.model.BankSlipsDetailsDTO;
import com.bankslipsrest.model.BankSlipsPayDTO;
import com.bankslipsrest.model.BankSlipsPostDTO;
import com.bankslipsrest.repository.BankSlipsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankSlipsService{

    @Autowired
    private BankSlipsRepository repository;

    public BankSlipsDetailsDTO getById(UUID id){
        BankSlips entity = repository.getOne(id);

        BankSlipsDetailsDTO dto = new BankSlipsDetailsDTO();
        dto.setId(entity.getId());
        dto.setDueDate(entity.getDueDate());
        dto.setPayment_date(entity.getPaymentDate());
        dto.setTotalInCents(entity.getTotalInCents());
        dto.setCustomer(entity.getCustomer());
        dto.setStatus(entity.getStatus());
        dto.setFine(calculateFine(entity));
        return dto;
    }

    private BigDecimal calculateFine(BankSlips entity) {
        LocalDate dueDate = toLocalDate(entity.getDueDate());
        int delayedDays = Period.between(dueDate, LocalDate.now()).getDays();

        BigDecimal finePercent = BigDecimal.ONE;

        if(delayedDays <= 10)
            finePercent = BigDecimal.valueOf(0.5);

        return entity.getTotalInCents().add(finePercent.multiply(BigDecimal.valueOf(delayedDays)));
    }

    private LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    } 

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    public BankSlips createBankSlips(BankSlipsPostDTO dto){
        BankSlips entity = new BankSlips();
        entity.setDueDate(dto.getDueDate());
        entity.setTotalInCents(dto.getTotalInCents());
        entity.setCustomer(dto.getCustomer());

        return repository.save(entity);
    }

    public BankSlips cancelBankSlips(BankSlipsCancelDTO dto)
    {       
        BankSlips entity = repository.getOne(dto.getId());        
        entity.setStatus(dto.getStatus());
        return repository.saveAndFlush(entity);
    }

    public BankSlips payBankSlips(UUID id, BankSlipsPayDTO dto)
    {       
        BankSlips entity = repository.getOne(id);        
        entity.setDueDate(dto.getPaymentDate());
        return repository.saveAndFlush(entity);
    }
}