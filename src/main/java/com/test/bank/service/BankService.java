package com.test.bank.service;

import com.test.bank.entity.Bank;
import com.test.bank.repository.BankRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BankService {
    private final BankRepository bankRepository;

    public void saveBank(Bank bank) {
        bankRepository.save(bank);
    }

    public void deleteBank(UUID id) {
        bankRepository.deleteById(id);
    }

    public List<Bank> findAll() {
        return bankRepository.findAll();
    }

    public Bank findById(UUID id) {
        return bankRepository.getById(id);
    }
}
