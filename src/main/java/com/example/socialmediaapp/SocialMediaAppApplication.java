package com.example.socialmediaapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SocialMediaAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialMediaAppApplication.class, args);
    }

}
