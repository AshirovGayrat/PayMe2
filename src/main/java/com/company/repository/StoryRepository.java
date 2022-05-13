package com.company.repository;

import com.company.entity.StoryEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface StoryRepository extends PagingAndSortingRepository<StoryEntity, Long> {
    List<StoryEntity> findByFromPhone(String phone);

    List<StoryEntity> findByToPhone(String phone);
}
