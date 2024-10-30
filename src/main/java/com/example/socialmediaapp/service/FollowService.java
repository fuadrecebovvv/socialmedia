package com.example.socialmediaapp.service;

import com.example.socialmediaapp.dao.entities.FollowEntity;
import com.example.socialmediaapp.dao.repositories.FollowRepository;
import com.example.socialmediaapp.dao.repositories.UserRepository;
import com.example.socialmediaapp.mapper.FollowMapper;
import com.example.socialmediaapp.model.FollowDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FollowService {
    private final FollowRepository followRepository;
    private final FollowMapper followMapper;
    private final UserRepository userRepository;

    public void followUser(Long followerId, Long followingId){
        log.info("FollowService.followUser.start: followerId {} followingId {}", followerId, followingId);
        var follower = userRepository.findById(followerId).orElseThrow();
        var following = userRepository.findById(followingId).orElseThrow();

        var followEntity = new FollowEntity();

        followEntity.setFollower(follower);
        followEntity.setFollowing(following);

        followRepository.save(followEntity);
        log.info("FollowService.followUser.end: followerId {} followingId {}", followerId, followingId);
    }

    public void unfollowUser(Long followerId, Long followingId){
        log.info("FollowService.unfollowUser.start: followerId {} followingId {}", followerId, followingId);
        var followEntity = followRepository.UserFollowing(followerId)
                .stream()
                .filter(followings -> followings
                                .getFollowing()
                                .getId()
                                .equals(followingId))
                .findFirst()
                .orElseThrow();
        followRepository.delete(followEntity);
        log.info("FollowService.unfollowUser.end followerId {} followingId {}", followerId, followingId);
    }

    public List<FollowDto> myFollowers(Long userId){
        log.info("FollowService.myFollowers.start userId {}", userId);
        List<FollowDto> followers = followRepository.UserFollowers(userId)
                .stream()
                .map(followMapper::toDto)
                .toList();
        log.info("FollowService.myFollowers.end userId {}", userId);
        return followers;
    }

    public List<FollowDto> myFollowings(Long userId){
        log.info("FollowService.myFollowings.start userId {}", userId);
        List<FollowDto> followings = followRepository.UserFollowing(userId)
                .stream()
                .map(followMapper::toDto)
                .toList();
        log.info("FollowService.myFollowings.end userId {}", userId);
        return followings;
    }


}
