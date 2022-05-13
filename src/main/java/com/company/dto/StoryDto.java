package com.company.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StoryDto {
    private Long id;
    private String fromCard, toCard;
    private String toName, fromName;
    private Long amount;
    private LocalDateTime time;
}
