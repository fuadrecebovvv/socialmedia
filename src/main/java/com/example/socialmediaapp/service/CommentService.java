package com.example.socialmediaapp.service;

import com.example.socialmediaapp.dao.repositories.CommentRepository;
import com.example.socialmediaapp.dao.repositories.PostRepository;
import com.example.socialmediaapp.dao.repositories.UserRepository;
import com.example.socialmediaapp.mapper.CommentMapper;
import com.example.socialmediaapp.model.CommentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public void addComment(CommentDto commentDto){
        log.info("CommentService.addComment.start");
        var user = userRepository.findById(commentDto.getUserId()).orElseThrow();
        var post = postRepository.findById(commentDto.getPostId()).orElseThrow();
        var commentEntity = commentMapper.toEntity(commentDto, user, post);
        commentRepository.save(commentEntity);
        log.info("CommentService.addComment.end");
    }

    public List<CommentDto> commentsOfPost(Long postId){
        log.info("CommentService.commentsOfPost.start");
        return commentRepository.commentsOfPost(postId)
                .stream()
                .map(commentMapper::toDto).toList();
    }

    public void likeComment(Long commentId, Long userId){
        log.info("CommentService.likeComment.start");
        var commentEntity = commentRepository.findById(commentId).orElseThrow();
        if (commentEntity.getCommentLikedUserIds().contains(userId)){
            commentEntity.getCommentLikedUserIds().remove(userId);
            commentRepository.save(commentEntity);
        }else {
            commentEntity.getCommentLikedUserIds().add(userId);
            commentRepository.save(commentEntity);
        }
        log.info("CommentService.likeComment.end");
    }

    public void responseComment(Long mainCommentId, CommentDto commentDto){
        log.info("CommentService.responseComment.start");
        var user = userRepository.findById(commentDto.getUserId()).orElseThrow();
        var post = postRepository.findById(commentDto.getPostId()).orElseThrow();
        var commentEntity = commentRepository.findById(mainCommentId).orElseThrow();

        var response = commentMapper.toEntity(commentDto, user, post);
        response.setMainComment(commentEntity);
        commentRepository.save(response);
        log.info("CommentService.responseComment.end");
    }

    public List<CommentDto> responsesOfComment(Long commentId){
        log.info("CommentService.responsesOfComment.start");
        var responses = commentRepository.responsesOfComment(commentId);
        log.info("CommentService.responsesOfComment.end");
        return responses;
    }
}
