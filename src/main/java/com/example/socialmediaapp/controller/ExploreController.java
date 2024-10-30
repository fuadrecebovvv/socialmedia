package com.example.socialmediaapp.controller;

import com.example.socialmediaapp.model.PostDto;
import com.example.socialmediaapp.service.ExploreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/explore")
@RequiredArgsConstructor
public class ExploreController {
    public final ExploreService exploreService;

    @GetMapping
    public List<PostDto> publicProfilePosts(){
        return exploreService.publicProfilePosts();
    }


}
