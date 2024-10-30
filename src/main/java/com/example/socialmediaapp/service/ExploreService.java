package com.example.socialmediaapp.service;

import com.example.socialmediaapp.dao.repositories.PostRepository;
import com.example.socialmediaapp.dao.repositories.UserRepository;
import com.example.socialmediaapp.mapper.PostMapper;
import com.example.socialmediaapp.mapper.UserMapper;
import com.example.socialmediaapp.model.PostDto;
import com.example.socialmediaapp.model.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExploreService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<PostDto> publicProfilePosts(){
        log.info("ExploreService.publicProfilePosts.start");
        var posts =  postRepository.publicProfilePosts()
                .stream()
                .map(postMapper::mapToDto)
                .toList();
        log.info("ExploreService.publicProfilePosts.end");
        return posts;
    }

    public List<UserDto> searchUser(String username){
        log.info("ExploreService.searchUser.start");
        var users =  userRepository.searchUser(username)
                .stream()
                .map(userMapper::mapToDto)
                .toList();
        log.info("ExploreService.searchUser.end");
        return users;
    }

}
