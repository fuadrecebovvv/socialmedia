package com.example.socialmediaapp.mapper;

import com.example.socialmediaapp.dao.entities.CommentEntity;
import com.example.socialmediaapp.dao.entities.PostEntity;
import com.example.socialmediaapp.dao.entities.UserEntity;
import com.example.socialmediaapp.model.CommentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;

@Mapper(componentModel = "spring", imports = LocalDate.class)
public abstract class CommentMapper {

    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "postId", source = "post.id")
    public abstract CommentDto toDto(CommentEntity commentEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "commentLikedUserIds", source = "commentDto.likedUserIds")
    @Mapping(target = "post", source = "post")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "responses", ignore = true)
    @Mapping(target = "createdAt", expression = "java(LocalDate.now())")
    public abstract CommentEntity toEntity(CommentDto commentDto, UserEntity user, PostEntity post);
}
