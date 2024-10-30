package com.example.socialmediaapp.service;

import com.example.socialmediaapp.mapper.FollowMapper;
import com.example.socialmediaapp.model.FollowDto;
import com.example.socialmediaapp.model.PostDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HomePageService {
    private final FollowService followService;
    private final PostService postService;

    public List<PostDto> recentPosts(Long userId){
        log.info("HomePageService.recentPosts.start userId {}", userId);
        var followings = followService.myFollowings(userId).stream()
                .map(FollowDto::getFollowingId).toList();
        var posts =  postService.recentPosts(followings);
        log.info("HomePageService.recentPosts.end userId {}", userId);
        return posts;
    }
}
