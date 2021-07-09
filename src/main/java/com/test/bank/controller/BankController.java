package com.test.bank.controller;

import com.test.bank.entity.Bank;
import com.test.bank.entity.Client;
import com.test.bank.entity.Credit;
import com.test.bank.service.BankService;
import com.test.bank.service.ClientService;
import com.test.bank.service.CreditService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class BankController {
    private final BankService bankService;
    private final ClientService clientService;
    private final CreditService creditService;

    @GetMapping("/banks")
    public String findAll(Model model) {
        List<Bank> allBanks = bankService.findAll();
        model.addAttribute("banks", allBanks);
        return "bank-list";
    }

    @GetMapping("/bank-create")
    public String createBankForm(Bank bank) {
        return "bank-create";
    }

    @PostMapping("/bank-create")
    public String createBank(Bank bank) {
        bankService.saveBank(bank);
        return "redirect:/banks";
    }

    @GetMapping("/bank-update/{id}")
    public String updateBankForm(@PathVariable("id") UUID id, Model model) {
        Bank bank = bankService.findById(id);
        model.addAttribute("bank", bank);
        return "/bank-update";
    }

    @PostMapping("/bank-update")
    public String updateBank(Bank bank) {
        bankService.saveBank(bank);
        return "redirect:/banks";
    }

    @GetMapping("/bank-delete/{id}")
    public String deleteBank(@PathVariable("id") UUID id) {
        bankService.deleteBank(id);
        return "redirect:/banks";
    }

    @GetMapping("/show-clients/{id}")
    public String showClients(@PathVariable("id") UUID id, Model model) {
        Bank bank = bankService.findById(id);
        List<Client> listClient = clientService.findAllByBankId(id);
        bank.setClients(listClient);
        model.addAttribute("bank", bank);
        return "/show-clients";
    }

    @GetMapping("/show-credits/{id}")
    public String showCredits(@PathVariable("id") UUID id, Model model) {
        Bank bank = bankService.findById(id);
        List<Credit> listCredit = creditService.findAllByBankId(id);
        bank.setCredits(listCredit);
        model.addAttribute("bank", bank);
        return "/show-credits";
    }
}
