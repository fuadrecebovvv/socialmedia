package com.example.socialmediaapp.controller;

import com.example.socialmediaapp.model.PostDto;
import com.example.socialmediaapp.service.HomePageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomePageController {
    public final HomePageService homePageService;

    @GetMapping("/{userId}")
    public List<PostDto> recentPosts(@PathVariable Long userId){
        return homePageService.recentPosts(userId);
    }
}
