package com.company.service;

import com.company.dto.TransactionDto;
import com.company.entity.CardEntity;
import com.company.entity.StoryEntity;
import com.company.exp.BalanceNotEnoughException;
import com.company.exp.ItemNotFoundException;
import com.company.exp.TransactionNotSaccess;
import com.company.repository.CardRepository;
import com.company.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransaktionService {
    private final String companyCard = "8600999999999999";
    @Autowired
    private StoryRepository transaktionRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private LoginService loginService;

    public String saveTran(TransactionDto dto) {
        Long fromBalance = cardRepository.getBalanceByPhone(loginService.getPhone());
        if (fromBalance == null) {
            throw new ItemNotFoundException("From Card Not found");
        }

        Long toCardBalance=cardRepository.getBalance(dto.getToCardNumber());
        if (toCardBalance==null){
            throw new ItemNotFoundException("ToCard not found");
        }

        long percent = dto.getSumma() / 100;
        if (fromBalance<(dto.getSumma()+percent)) {
            throw new BalanceNotEnoughException("Balance not enough");
        }

        CardEntity fromCard=cardRepository.findByPhone(loginService.getPhone());
        CardEntity toCard=cardRepository.findByNumber(dto.getToCardNumber());

        StoryEntity storyEntity = new StoryEntity();
        storyEntity.setFromPhone(loginService.getPhone());
        storyEntity.setToPhone(toCard.getPhone());
        storyEntity.setToCard(dto.getToCardNumber());
        storyEntity.setAmount(dto.getSumma());
        storyEntity.setFromCard(fromCard.getNumber());
        storyEntity.setToName(toCard.getName() + " " + toCard.getSurname());
        storyEntity.setFromName(fromCard.getName() + " " + fromCard.getSurname());
        storyEntity.setComent(dto.getComent());
        transaktionRepository.save(storyEntity);

        Long saveFromBalance=fromBalance-(dto.getSumma()+percent);
        Long saveToBalance=toCardBalance+dto.getSumma();
        int n = cardRepository.updateBalanceByPhone(saveFromBalance, loginService.getPhone());
        int m = cardRepository.updateBalanceByNumber(saveToBalance, dto.getToCardNumber());
        if (!(n>0 && m>0)){
            throw new TransactionNotSaccess("Transaction Not Saccess");
        }

        Long companyBalance=cardRepository.getBalance(companyCard);
        Long saveCompanyBalance=companyBalance+percent;
        cardRepository.updateBalanceByNumber(saveCompanyBalance, companyCard);

        return "god";
    }

    public TransactionDto getById(Long id) {
        Optional<StoryEntity> optional = transaktionRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Story not found");
        }
        StoryEntity entity = optional.get();
        TransactionDto dto = new TransactionDto();
        dto.setId(entity.getId());
        dto.setToCardNumber(entity.getToCard());
        dto.setSumma(entity.getAmount());
        return dto;
    }

}
