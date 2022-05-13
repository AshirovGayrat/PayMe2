package com.company.controller;

import com.company.dto.BalanceDto;
import com.company.entity.StoryEntity;
import com.company.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BalanceController {
    @Autowired
    private BalanceService balanceService;

    @GetMapping("/balance")
    public String getBalance(Model model) {
        BalanceDto balance = balanceService.getBalance();
        model.addAttribute("balance", balance);
        return "balance";
    }

    @GetMapping("/adm/balance")
    public String getAdmBalance(Model model) {
        BalanceDto balance = balanceService.getBalance();
        model.addAttribute("balance", balance);
        return "adm_balance";
    }
}
