package com.company.dto;

import lombok.Data;

@Data
public class TransactionDto {
    private Long id;
    private String toCardNumber;
    private Long summa;
    private String coment;
}
