package com.example.socialmediaapp.controller;

import com.example.socialmediaapp.model.FollowDto;
import com.example.socialmediaapp.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follows")
@RequiredArgsConstructor
public class FollowController {
    public final FollowService followService;

    @PostMapping
    public void followUser(@RequestParam Long followerId, @RequestParam Long followingId){
        followService.followUser(followerId, followingId);
    }

    @DeleteMapping
    public void unfollowUser(@RequestParam Long followerId, @RequestParam Long followingId){
        followService.unfollowUser(followerId, followingId);
    }

    @GetMapping("/followers/{userId}")
    public List<FollowDto> myFollowers(@PathVariable Long userId){
        return followService.myFollowers(userId);
    }

    @GetMapping("/followings/{userId}")
    public List<FollowDto> myFollowings(@PathVariable Long userId){
        return followService.myFollowings(userId);
    }
}
