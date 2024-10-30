package com.example.socialmediaapp.service;

import com.example.socialmediaapp.dao.repositories.PostRepository;
import com.example.socialmediaapp.dao.repositories.UserRepository;
import com.example.socialmediaapp.mapper.PostMapper;
import com.example.socialmediaapp.model.PostDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public void addPost(Long userId, PostDto postDto){
        log.info("PostService.addPost.start userId {}", userId);
        var userEntity = userRepository.findById(userId).orElseThrow();
        var postEntity = postMapper.mapToEntity(postDto);
        postEntity.setUser(userEntity);
        postEntity.setLikeCount(0);
        postEntity.setCreatedAt(LocalDateTime.now());
        postRepository.save(postEntity);
        log.info("PostService.addPost.end userId {}", userId);
    }

    public List<PostDto> allPosts(Long userId){
        log.info("PostService.allPosts.start userId {}", userId);
        var userEntity = userRepository.findById(userId).orElseThrow();
        List<PostDto> postDtos = userEntity.getPosts().stream()
                .map(postMapper::mapToDto).toList();
        log.info("PostService.allPosts.end userId {}", userId);
        return postDtos;
    }

    public void deletePost(Long postId){
        log.info("PostService.deletePost.start postId {}", postId);
        postRepository.deleteById(postId);
        log.info("PostService.deletePost.end postId {}", postId);
    }

    public void updatePost(Long id, PostDto postDto){
        log.info("PostService.updatePost.start");
        var postEntity = postRepository.findById(id).orElseThrow();
        postMapper.toEntity(postDto, postEntity);
        postRepository.save(postEntity);
        log.info("PostService.updatePost.end");
    }

    public Integer howManyLikes(Long postId){
        log.info("PostService.howManyLikes.start postId {}", postId);
        var postEntity = postRepository.findById(postId).orElseThrow();
        var likes = postEntity.getLikeCount();
        log.info("PostService.howManyLikes.end postId {}", postId);
        return likes;
    }

    public List<PostDto> recentPosts(List<Long> followingsId){
        log.info("PostService.recentPosts.start");
        var posts = postRepository.recentPosts(followingsId).stream().map(postMapper::mapToDto).toList();
        log.info("PostService.recentPosts.start");
        return posts;
    }


}
