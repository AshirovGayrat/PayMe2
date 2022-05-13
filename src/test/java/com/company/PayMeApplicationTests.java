package com.company;

import com.company.dto.ProfileDto;
import com.company.repository.ProfileRepository;
import com.company.service.LoginService;
import com.company.service.StoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PayMeApplicationTests {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private LoginService loginService;
    @Autowired
    private StoryService storyService;

    @Test
    void contextLoads() {
//        System.out.println(profileRepository.checkProfile("932052919", "simba"));
//        System.out.println(profileRepository.getByCardNumber("86009999999999").get().getName());
        System.out.println("result:: "+profileRepository.getVisible("932052919"));
    }
}
