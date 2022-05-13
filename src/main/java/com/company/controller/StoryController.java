package com.company.controller;

import com.company.entity.StoryEntity;
import com.company.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StoryController {
    @Autowired
    private StoryService storyService;

    @GetMapping("/story")
    public String chiqimStoryList(Model model) {
        Iterable<StoryEntity> story = storyService.getChiqimStoryByPhone();
        model.addAttribute("story", story);
        return "story";
    }

    @GetMapping("/story/{id}")
    public String deleteChiqimStory(@PathVariable Long id){
        storyService.deleteStory(id);
        return "redirect:/story";
    }

    ///// Tushum story
    @GetMapping("/tushum")
    public String tushumStoryList(Model model) {
        Iterable<StoryEntity> story = storyService.getTushumStoryByPhone();
        model.addAttribute("story", story);
        return "tushum";
    }

    @GetMapping("/tushum/{id}")
    public String deleteTushumStory(@PathVariable Long id){
        storyService.deleteStory(id);
        return "redirect:/tushum";
    }
}
