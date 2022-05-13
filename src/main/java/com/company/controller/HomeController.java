package com.company.controller;

import com.company.entity.StoryEntity;
import com.company.service.HomeService;
import com.company.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    @Autowired
    private HomeService homeService;
    @Autowired
    private StoryService storyService;

    @GetMapping("/last/story")
    public String homeWithStory(Model model) {
        Iterable<StoryEntity> story = storyService.getChiqimStoryByPhone();
        model.addAttribute("story", story);
        return "home2";
    }

    @GetMapping("/open")
    public String home() {
        return "home";
    }

    @GetMapping("/delete/{id}")
    public String deleteChiqimStory(@PathVariable Long id){
        storyService.deleteStory(id);
        return "redirect:/last/story";
    }

    @GetMapping("/adm/home")
    public String admGoToHome(){
        return "admin_panel";
    }

}
