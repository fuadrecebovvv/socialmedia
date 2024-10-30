package com.example.socialmediaapp.controller;

import com.example.socialmediaapp.model.PostDto;
import com.example.socialmediaapp.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    public final PostService postService;

    @GetMapping("/users/{userId}")
    public List<PostDto> allPosts(@PathVariable Long userId){
        return postService.allPosts(userId);
    }

    @PostMapping("/users/{userId}")
    public void addPost(@PathVariable Long userId, @RequestBody PostDto postDto){
        postService.addPost(userId, postDto);
    }

    @PutMapping("/{postId}")
    public void updatePost(@PathVariable Long postId, PostDto postDto){
        postService.updatePost(postId, postDto);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
    }

    @GetMapping("/{postId}")
    public Integer howManyLikes(@PathVariable Long postId){
        return postService.howManyLikes(postId);
    }
}
