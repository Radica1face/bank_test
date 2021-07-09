package com.test.bank.repository;

import com.test.bank.entity.CreditSupply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CreditSupplyRepository extends JpaRepository<CreditSupply, UUID> {

}
