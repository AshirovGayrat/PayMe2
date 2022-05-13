package com.company.repository;

import com.company.entity.ProfileEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends PagingAndSortingRepository<ProfileEntity, Long> {

    Optional<ProfileEntity> findByPhoneAndPassword(String phone, String password);

    ProfileEntity findByCardNumber(String cardNumber);

    Optional<ProfileEntity> findByPhone(String cardNumber);

    @Query(value = "select pe.visible from profile_entity as pe where phone = ?", nativeQuery = true)
    Boolean getVisible(String phone);
}
