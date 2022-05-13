package com.company.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "story_transaction")
public class StoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_card")
    private String fromCard;
    @Column(name = "to_card")
    private String toCard;
    @Column(name = "from_phone")
    private String fromPhone;
    @Column(name = "to_phone")
    private String toPhone;
    private String coment;

    @Column(name = "to_name")
    private String toName;
    @Column(name = "from_name")
    private String fromName;

    private Long amount;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate = LocalDateTime.now();
}
