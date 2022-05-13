package com.company.repository;

import com.company.entity.CardEntity;
import com.company.enums.ProfileStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface CardRepository extends PagingAndSortingRepository<CardEntity, Long> {

    CardEntity findByNumber(String number);

    CardEntity findByPhone(String phone);

    @Query(value = "select balance from card_table where number = ?", nativeQuery = true)
    Long getBalance(String number);


    @Query(value = "select balance from card_table where phone = ?", nativeQuery = true)
    Long getBalanceByPhone(String phone);

    @Transactional
    @Modifying
    @Query("update CardEntity set balance = :amount where number = :num")
    int updateBalanceByNumber(@Param("amount") Long amount, @Param("num") String number);

    @Transactional
    @Modifying
    @Query("update CardEntity set balance = :amount where phone = :phone")
    int updateBalanceByPhone(@Param("amount") Long amount, @Param("phone") String phone);

}
