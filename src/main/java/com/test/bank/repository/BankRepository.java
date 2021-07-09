package com.test.bank.repository;

import com.test.bank.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BankRepository extends JpaRepository<Bank, UUID> {
}
