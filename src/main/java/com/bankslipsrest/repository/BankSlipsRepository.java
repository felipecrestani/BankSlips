package com.bankslipsrest.repository;

import java.util.UUID;

import com.bankslipsrest.model.BankSlips;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankSlipsRepository extends JpaRepository<BankSlips,UUID> {

}
