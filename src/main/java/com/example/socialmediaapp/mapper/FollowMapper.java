package com.example.socialmediaapp.mapper;

import com.example.socialmediaapp.dao.entities.FollowEntity;
import com.example.socialmediaapp.model.FollowDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FollowMapper {

    @Mapping(target = "follower.id", source = "followerId")
    @Mapping(target = "following.id", source = "followingId")
    FollowEntity toEntity(FollowDto followDto);

    @Mapping(target = "followerId", source = "follower.id")
    @Mapping(target = "followingId", source = "following.id")
    @Mapping(target = "followerUsername", source = "follower.username")
    @Mapping(target = "followingUsername", source = "following.username")
    FollowDto toDto(FollowEntity followEntity);
}
