package com.test.bank.service;

import com.test.bank.entity.CreditSupply;
import com.test.bank.repository.CreditSupplyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CreditSupplyService {
    private final CreditSupplyRepository creditSupplyRepository;

    public void saveSupply(CreditSupply creditSupply) {
        creditSupplyRepository.save(creditSupply);
    }

    public void deleteSupply(UUID id) {
        creditSupplyRepository.deleteById(id);
    }

    public List<CreditSupply> findAll() {
        return creditSupplyRepository.findAll();
    }

    public CreditSupply findById(UUID id) {
        return creditSupplyRepository.getById(id);
    }
}
