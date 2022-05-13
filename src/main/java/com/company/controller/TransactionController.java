package com.company.controller;

import com.company.dto.TransactionDto;
import com.company.entity.StoryEntity;
import com.company.service.StoryService;
import com.company.service.TransaktionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TransactionController {
    @Autowired
    private TransaktionService transaktionService;
    @Autowired
    private StoryService storyService;

    @GetMapping("/tr/new")
    public String transaction(Model model) {
        TransactionDto transaction = new TransactionDto();
        model.addAttribute("transaction", transaction);
        return "transaction";
    }

    @PostMapping("/transaction")
    public String saveTransaction(@ModelAttribute("transaction") TransactionDto transaction) {
        return transaktionService.saveTran(transaction);
    }

    @GetMapping("/toHome")
    public String toHome() {
        return "home";
    }

    @GetMapping("/transaction/edit/{id}")
    public String reTransaction(@PathVariable("id") Long id, Model model) {
        model.addAttribute("transaction", transaktionService.getById(id));
        return "re_transaction";
    }

    @PostMapping("/transaction/{id}")
    public String updateTransaction(@PathVariable Long id, @ModelAttribute("transaction") TransactionDto transaction,
                                Model model) {
        transaktionService.saveTran(transaction);
        return "redirect:/last/story";
    }

    @GetMapping("/transactions")
    public String getAllTransactions(Model model) {
        Iterable<StoryEntity> transactions = storyService.getAll();
        model.addAttribute("transactions", transactions);
        return "all_transactions";
    }

    @GetMapping("/adm/delete/{id}")
    public String deleteTransaction(@PathVariable Long id){
        storyService.deleteStory(id);
        return "redirect:/tushum";
    }

}
