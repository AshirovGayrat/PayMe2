package com.company.controller;

import com.company.dto.LoginDto;
import com.company.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping("/login/new")
    public String login(Model model) {
        LoginDto login=new LoginDto();
        model.addAttribute("login", login);
        return "login";
    }

    @PostMapping("/login")
    public String loginCheck(@ModelAttribute("login") LoginDto login) {
        return loginService.login(login);
    }
}
