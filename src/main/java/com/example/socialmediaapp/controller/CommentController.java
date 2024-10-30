package com.example.socialmediaapp.controller;

import com.example.socialmediaapp.model.CommentDto;
import com.example.socialmediaapp.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    public final CommentService commentService;

    @PostMapping
    public void addComment(@RequestBody CommentDto commentDto){
        commentService.addComment(commentDto);
    }

    @GetMapping("/post/{postId}")
    public List<CommentDto> commentsOfPost(@PathVariable Long postId){
        return commentService.commentsOfPost(postId);
    }

    @GetMapping("/{commentId}")
    public List<CommentDto> responsesOfComment(@PathVariable Long commentId){
        return commentService.responsesOfComment(commentId);
    }

    @PutMapping("/{commentId}/user/{userId}")
    public void likeComment(@PathVariable Long commentId, @PathVariable Long userId){
        commentService.likeComment(commentId, userId);
    }

    @PutMapping("/{mainCommentId}")
    public void responseComment(@PathVariable Long mainCommentId, @RequestBody CommentDto commentDto){
        commentService.responseComment(mainCommentId, commentDto);
    }


}
