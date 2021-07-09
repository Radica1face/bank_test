package com.test.bank.service;

import com.test.bank.entity.Client;
import com.test.bank.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;


@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public void saveClient(Client client) {
        clientRepository.save(client);
    }

    public void deleteClient(UUID id) {
        clientRepository.deleteById(id);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(UUID id) {
        return clientRepository.getById(id);
    }

    public List<Client> findAllByBankId(UUID bank_id) {
        return clientRepository.findAllByBankId(bank_id);
    }
}
