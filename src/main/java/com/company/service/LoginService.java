package com.company.service;

import com.company.dto.LoginDto;
import com.company.entity.ProfileEntity;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.company.exp.ItemNotFoundException;
import com.company.exp.PasswordOrPhoneWrongException;
import com.company.exp.ProfileBlockedException;
import com.company.repository.ProfileRepository;
import com.company.validation.ProfileValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private ProfileRepository profileRepository;

    private String phone;

    public String getPhone() {
        return phone;
    }


    public String login(LoginDto login) {

        ProfileValidation.loginValid(login);

        Optional<ProfileEntity> optional = profileRepository.
                findByPhoneAndPassword(login.getPhone(), login.getPassword());

        if (optional.isEmpty()) {
            throw new PasswordOrPhoneWrongException("Password or Phone Wrong!");
        }
        ProfileEntity entity = optional.get();

        if (!entity.getVisible()) {
            throw new ItemNotFoundException("Not found!");
        }

        if (!entity.getStatus().equals(ProfileStatus.ACTIVE)) {
            throw new ProfileBlockedException("Profile Blocked!");
        }
        phone = login.getPhone();

        if (entity.getRole().equals(ProfileRole.ADMIN)){
            return "admin_panel";
        }

        return "home";
    }


}
