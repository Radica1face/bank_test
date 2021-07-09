package com.test.bank.controller;

import com.test.bank.business.PaymentGraph;
import com.test.bank.entity.Client;
import com.test.bank.entity.Credit;
import com.test.bank.entity.CreditSupply;
import com.test.bank.business.RowGraph;
import com.test.bank.service.ClientService;
import com.test.bank.service.CreditService;
import com.test.bank.service.CreditSupplyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;

@Controller
@AllArgsConstructor
public class SupplyController {
    private final CreditSupplyService creditSupplyService;
    private final CreditService creditService;
    private final ClientService clientService;

    @GetMapping("/supplies")
    public String findAll(Model model) {
        List<CreditSupply> allSupplies = creditSupplyService.findAll();
        model.addAttribute("supplies", allSupplies);
        return "supply-list";
    }

    @GetMapping("/supply-create")
    public String createSupplyForm(CreditSupply creditSupply, Model model) {
        List<Client> clients = clientService.findAll();
        List<Credit> credits = creditService.findAll();
        model.addAttribute("clients", clients);
        model.addAttribute("credits", credits);
        return "supply-create";
    }

    @PostMapping("/supply-create")
    public String createSupply(@RequestParam UUID clientId,
                               @RequestParam UUID creditId,
                               @RequestParam Double loanMoney,
                               @RequestParam Integer years,
                               @RequestParam String date,
                               Model model) throws ParseException {

        Client client = clientService.findById(clientId);
        Credit credit = creditService.findById(creditId);

        Double yIns = credit.getInterestRate();
        PaymentGraph paymentGraph = new PaymentGraph();
        List<RowGraph> listGraph = paymentGraph.makeGraph(yIns, loanMoney, years, date);

        Double totalInterests = paymentGraph.getTotalInterests();

        CreditSupply creditSupply = new CreditSupply(client, credit, loanMoney, years, date);

        creditSupplyService.saveSupply(creditSupply);

        model.addAttribute("listGraph", listGraph);
        model.addAttribute("totalInterests", totalInterests);

        return "show-graph";
    }

    @GetMapping("/supply-update/{id}")
    public String updateSupplyForm(@PathVariable("id") UUID id, Model model) {
        List<Client> clients = clientService.findAll();
        List<Credit> credits = creditService.findAll();
        model.addAttribute("clients", clients);
        model.addAttribute("credits", credits);
        CreditSupply creditSupply = creditSupplyService.findById(id);
        model.addAttribute("creditSupply", creditSupply);
        return "/supply-update";
    }

    @PostMapping("/supply-update")
    public String updateSupply(CreditSupply creditSupply) {
        creditSupplyService.saveSupply(creditSupply);
        return "redirect:/supplies";
    }

    @GetMapping("/supply-delete/{id}")
    public String deleteSupply(@PathVariable("id") UUID id) {
        creditSupplyService.deleteSupply(id);
        return "redirect:/supplies";
    }

    @GetMapping("/show-graph/{id}")
    public String showGraph(@PathVariable("id") UUID id, Model model) {
        CreditSupply supply = creditSupplyService.findById(id);

        Double yIns = supply.getCredit().getInterestRate();
        Double loanMoney = supply.getLoanMoney();
        Integer years = supply.getYears();
        String date = supply.getDate();

        PaymentGraph paymentGraph = new PaymentGraph();
        List<RowGraph> listGraph = paymentGraph.makeGraph(yIns, loanMoney, years, date);

        Double totalInterests = paymentGraph.getTotalInterests();

        model.addAttribute("listGraph", listGraph);
        model.addAttribute("totalInterests", totalInterests);
        return "show-graph";
    }
}
