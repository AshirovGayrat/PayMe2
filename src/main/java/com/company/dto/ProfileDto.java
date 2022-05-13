package com.company.dto;

import com.company.enums.ProfileStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProfileDto {
    private Long id;
    private String name, surname,phone,cardNumber,password;
    private LocalDateTime createdDate;
    private ProfileStatus status;
}
