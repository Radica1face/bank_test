package com.test.bank.repository;

import com.test.bank.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    List<Client> findAllByBankId(UUID bank_id);
}
