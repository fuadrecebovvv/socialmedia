package com.example.socialmediaapp.controller;

import com.example.socialmediaapp.model.LikeDto;
import com.example.socialmediaapp.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {
    public final LikeService likeService;

    @PostMapping
    public void addLike(@RequestBody LikeDto likeDto){
        likeService.addLike(likeDto);
    }

    @GetMapping
    public List<LikeDto> allLikes(){
        return likeService.allLikes();
    }

    @GetMapping("/post/{postId}")
    public List<LikeDto> getLikesByPost(@PathVariable Long postId){
        return likeService.getLikesByPost(postId);
    }

    @DeleteMapping
    public void deleteLike(@RequestBody LikeDto likeDto){
        likeService.deleteLike(likeDto);
    }
}
