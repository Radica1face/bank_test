package com.test.bank.controller;

import com.test.bank.entity.Bank;
import com.test.bank.entity.Client;
import com.test.bank.entity.Credit;
import com.test.bank.service.BankService;
import com.test.bank.service.CreditService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class CreditController {

    private final CreditService creditService;
    private final BankService bankService;

    @GetMapping("/credits")
    public String findAll(Model model) {
        List<Credit> allCredits = creditService.findAll();
        model.addAttribute("credits", allCredits);
        return "credit-list";
    }

    @GetMapping("/credit-create")
    public String createCreditForm(Credit credit, Model model) {
        List<Bank> banks = bankService.findAll();
        model.addAttribute("banks", banks);
        return "credit-create";
    }

    @PostMapping("/credit-create")
    public String createCredit(@Valid Credit credit, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            List<Bank> banks = bankService.findAll();
            model.addAttribute("banks", banks);
            return "credit-create";
        } else {
            creditService.saveCredit(credit);
            return "redirect:/credits";
        }

    }

    @GetMapping("/credit-update/{id}")
    public String updateCredittForm(@PathVariable("id") UUID id, Model model) {
        Credit credit = creditService.findById(id);
        model.addAttribute("credit", credit);
        return "/credit-update";
    }

    @PostMapping("/credit-update")
    public String updateCredit(@Valid Credit credit, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            List<Bank> banks = bankService.findAll();
            model.addAttribute("banks", banks);
            return "credit-update";
        } else {
            creditService.saveCredit(credit);
            return "redirect:/credits";
        }

    }

    @GetMapping("/credit-delete/{id}")
    public String deleteCredit(@PathVariable("id") UUID id) {
        creditService.deleteCredit(id);
        return "redirect:/credits";
    }
}
