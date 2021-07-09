package com.test.bank.service;

import com.test.bank.entity.Client;
import com.test.bank.entity.Credit;
import com.test.bank.repository.CreditRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CreditService {

    private final CreditRepository creditRepository;

    public void saveCredit(Credit credit) {
        creditRepository.save(credit);
    }

    public void deleteCredit(UUID id) {
        creditRepository.deleteById(id);
    }

    public List<Credit> findAll() {
        return creditRepository.findAll();
    }

    public Credit findById(UUID id) {
        return creditRepository.getById(id);
    }

    public List<Credit> findAllByBankId(UUID bank_id) {
        return creditRepository.findAllByBankId(bank_id);
    }

}
