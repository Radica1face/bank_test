package com.test.bank.repository;

import com.test.bank.entity.Client;
import com.test.bank.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CreditRepository extends JpaRepository<Credit, UUID> {
    List<Credit> findAllByBankId(UUID bank_id);
}
