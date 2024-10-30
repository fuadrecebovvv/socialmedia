package com.example.socialmediaapp.mapper;

import com.example.socialmediaapp.dao.entities.LikeEntity;
import com.example.socialmediaapp.model.LikeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LikeMapper {

    @Mapping(target = "post.id", source = "postId")
    @Mapping(target = "user.id", source = "userId")
    LikeEntity toEntity(LikeDto likeDto);

    @Mapping(target = "postId", source = "post.id")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "username", source = "user.username")
    LikeDto toDto(LikeEntity likeEntity);
}
