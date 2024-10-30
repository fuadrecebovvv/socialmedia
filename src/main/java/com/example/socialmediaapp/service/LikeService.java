package com.example.socialmediaapp.service;

import com.example.socialmediaapp.dao.repositories.LikeRepository;
import com.example.socialmediaapp.dao.repositories.PostRepository;
import com.example.socialmediaapp.dao.repositories.UserRepository;
import com.example.socialmediaapp.mapper.LikeMapper;
import com.example.socialmediaapp.model.LikeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LikeService {
    private final LikeMapper likeMapper;
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public void addLike(LikeDto likeDto){
        log.info("LikeService.addLike.start");
        var userEntity = userRepository.findById(likeDto.getUserId()).orElseThrow();
        var postEntity = postRepository.findById(likeDto.getPostId()).orElseThrow();
        var likeEntity = likeMapper.toEntity(likeDto);

        if (postEntity.getLikedUserIds().contains(likeDto.getUserId())){
            deleteLike(likeDto);
        } else {
            postEntity.setLikeCount(postEntity.getLikeCount() + 1);
            postEntity.getLikedUserIds().add(likeDto.getUserId());
            postRepository.save(postEntity);

            likeEntity.setUser(userEntity);
            likeEntity.setPost(postEntity);
            likeRepository.save(likeEntity);
        }
        log.info("LikeService.addLike.end");
    }

    public void deleteLike(LikeDto likeDto){
        log.info("LikeService.deleteLike.start");
        var likeEntity = likeMapper.toEntity(likeDto);
        var postEntity = postRepository.findById(likeDto.getPostId()).orElseThrow();

        postEntity.setLikeCount(postEntity.getLikeCount() - 1);
        postRepository.save(postEntity);

        likeRepository.delete(likeEntity);
        log.info("LikeService.deleteLike.end");
    }

    public List<LikeDto> allLikes(){
        log.info("LikeService.allLikes.start");
        List<LikeDto> likes = likeRepository.findAll()
                .stream().map(likeMapper::toDto).toList();
        log.info("LikeService.allLikes.end");
        return likes;
    }

    public List<LikeDto> getLikesByPost(Long postId){
        log.info("LikeService.getLikesByPost.start postId {}", postId);
        List<LikeDto> likes = likeRepository.findAll()
                .stream()
                .filter(
                        like -> like.getPost().getId()
                        .equals(postId))
                .map(likeMapper::toDto)
                .toList();
        log.info("LikeService.getLikesByPost.end postId {}", postId);
        return likes;
    }

}
