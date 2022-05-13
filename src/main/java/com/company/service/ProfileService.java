package com.company.service;

import com.company.dto.ProfileDto;
import com.company.entity.CardEntity;
import com.company.entity.ProfileEntity;
import com.company.enums.ProfileStatus;
import com.company.exp.ItemNotFoundException;
import com.company.exp.PhoneNotRegistered;
import com.company.exp.ProfileAlreadyExists;
import com.company.enums.ProfileRole;
import com.company.repository.CardRepository;
import com.company.repository.ProfileRepository;
import com.company.validation.ProfileValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private LoginService loginService;

    public String register(ProfileDto dto) {
        ProfileValidation.profValid(dto);

        Boolean visible = profileRepository.getVisible(dto.getPhone());
        if (visible != null && visible) {
            throw new ProfileAlreadyExists("PhoneNumber already exists!");
        }

        CardEntity card = cardRepository.findByNumber(dto.getCardNumber());
        if (card == null) {
            throw new ItemNotFoundException("Card not found!");
        }
        if (!card.getPhone().equals(dto.getPhone())) {
            throw new PhoneNotRegistered("SMS Xabarnoma yoqilmagan!");
        }

        profileRepository.save(toEntity(dto));

        if (loginService.getPhone()==null){
            return "redirect:/payme";
        }
        return "redirect:/adm/home";
    }

    public Iterable<ProfileDto> getAllProfiles() {
        List<ProfileDto> profileDtoList = new LinkedList<>();
        profileRepository.findAll().forEach(profile -> {
            profileDtoList.add(toDto(profile));
        });
        return profileDtoList;
    }

    public ProfileDto toDto(ProfileEntity entity) {
        ProfileDto dto = new ProfileDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setPhone(entity.getPhone());
        dto.setCardNumber(entity.getCardNumber());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public ProfileEntity toEntity(ProfileDto dto) {
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhone(dto.getPhone());
        entity.setCardNumber(dto.getCardNumber());
        entity.setPassword(dto.getPassword());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setStatus(ProfileStatus.ACTIVE);
        entity.setRole(ProfileRole.USER);
        return entity;
    }
}
