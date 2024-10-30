package com.example.socialmediaapp.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class SocialMediaScheduler {

    @Scheduled(cron = "10 51 * 9 8 5")
    public void scheduler(){
        System.out.println("time is: " + LocalTime.now());
    }
}
