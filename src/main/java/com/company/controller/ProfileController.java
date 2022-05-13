package com.company.controller;

import com.company.dto.ProfileDto;
import com.company.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping({"/payme", "/"})
    public String glavni() {
        return "payme";
    }

    @GetMapping("/register/new")
    public String createProfile(Model model) {
        ProfileDto profile = new ProfileDto();
        model.addAttribute("profile", profile);
        return "register";
    }

    @PostMapping("/register")
    public String saveProfile(@ModelAttribute("profile") ProfileDto profile) {
        //        return "redirect:/payme";
        return profileService.register(profile);
    }


    //ADM
    @GetMapping("/profiles")
    public String getAllProfile(Model model) {
        Iterable<ProfileDto> profile = profileService.getAllProfiles();
        model.addAttribute("profile", profile);
        return "all_profiles";
    }
}
