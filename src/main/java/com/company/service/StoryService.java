package com.company.service;

import com.company.entity.StoryEntity;
import com.company.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StoryService {
    @Autowired
    private StoryRepository storyRepository;
    @Autowired
    private LoginService loginService;

    public Iterable<StoryEntity> getChiqimStoryByPhone() {
        Iterable<StoryEntity> entities = storyRepository.findByFromPhone(loginService.getPhone());
        return entities;
    }

    public void deleteStory(Long id){
        storyRepository.deleteById(id);
    }

    //// get Tushum story
    public Iterable<StoryEntity> getTushumStoryByPhone() {
        Iterable<StoryEntity> entities = storyRepository.findByToPhone(loginService.getPhone());
        return entities;
    }

    public Iterable<StoryEntity> getAll(){
        return storyRepository.findAll();
    }
}
