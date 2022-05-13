package com.company.service;

import com.company.dto.BalanceDto;
import com.company.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BalanceService {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private LoginService loginService;

    public BalanceDto getBalance() {
        Long balance = cardRepository.getBalanceByPhone(loginService.getPhone());

        String str=String.valueOf(balance);

        ArrayList<Character> array=new ArrayList<>();

        int temp=1;
        for (int i = str.length()-1; i >= 0; i--) {
            array.add(str.charAt(i));
            if (temp==3){
                array.add('.');
                temp=0;
            }
            temp++;
        }
        StringBuilder builder=new StringBuilder();
        for(int i = array.size()-1;i>=0;i--){
            builder.append(array.get(i));
        }
        builder.append(" SUM");

        BalanceDto dto = new BalanceDto();
        dto.setBalance(builder.toString());

        return dto;
    }
}
