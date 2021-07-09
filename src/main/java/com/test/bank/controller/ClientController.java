package com.test.bank.controller;

import com.test.bank.entity.Bank;
import com.test.bank.entity.Client;
import com.test.bank.service.BankService;
import com.test.bank.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final BankService bankService;

    @GetMapping("/clients")
    public String findAll(Model model) {
        List<Client> allClients = clientService.findAll();
        model.addAttribute("clients", allClients);
        return "client-list";
    }

    @GetMapping("/client-create")
    public String createClientForm(Client client, Model model) {
        List<Bank> banks = bankService.findAll();
        model.addAttribute("banks", banks);
        return "client-create";
    }

    @PostMapping("/client-create")
    public String createClient(Client client) {
        clientService.saveClient(client);
        return "redirect:/clients";
    }

    @GetMapping("/client-update/{id}")
    public String updateClientForm(@PathVariable("id") UUID id, Model model) {
        Client client = clientService.findById(id);
        model.addAttribute("client", client);
        return "/client-update";
    }

    @PostMapping("/client-update")
    public String updateClient(Client client) {
        clientService.saveClient(client);
        return "redirect:/clients";
    }

    @GetMapping("/client-delete/{id}")
    public String deleteClient(@PathVariable("id") UUID id) {
        clientService.deleteClient(id);
        return "redirect:/clients";
    }
}
