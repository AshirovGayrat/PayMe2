package com.company.validation;

import com.company.dto.LoginDto;
import com.company.dto.ProfileDto;
import com.company.exp.BadRequestException;

public class ProfileValidation {

    public static void profValid(ProfileDto dto){
        if (dto.getName()==null || dto.getName().length()<3){
            throw new BadRequestException("Name not valid");
        }
        if (dto.getSurname()==null || dto.getSurname().length()<3){
            throw new BadRequestException("Surname not valid");
        }
        if (dto.getPassword()==null || dto.getPassword().length()<4){
            throw new BadRequestException("Password not valid");
        }
        if (dto.getPhone()==null || !(dto.getPhone().length()==9)){
            throw new BadRequestException("Phone not valid");
        }
        if (dto.getCardNumber()==null || !(dto.getCardNumber().length()==16)){
            throw new BadRequestException("Card number not valid");
        }
    }

    public static void loginValid(LoginDto dto){
        if (dto==null){
            throw new BadRequestException("Bad request");
        }
        if (dto.getPhone()==null || dto.getPhone().isEmpty()){
            throw new BadRequestException("Phone not valid");
        }
        if (dto.getPassword()==null || dto.getPassword().isEmpty()){
            throw new BadRequestException("Password not valid");
        }
    }

}
