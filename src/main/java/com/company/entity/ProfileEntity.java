package com.company.entity;

import com.company.enums.ProfileStatus;
import com.company.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "profile_entity")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    @Column(name = "phone", unique = true)
    private String phone;
    private String password;
    @Enumerated(EnumType.STRING)
    private ProfileStatus status;
    @Enumerated(EnumType.STRING)
    private ProfileRole role;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    private Boolean visible = true;

    @Column(name = "card_number", unique = true)
    private String cardNumber;
}
