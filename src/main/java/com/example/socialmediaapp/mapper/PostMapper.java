package com.example.socialmediaapp.mapper;

import com.example.socialmediaapp.dao.entities.PostEntity;
import com.example.socialmediaapp.model.PostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PostMapper {

    PostEntity mapToEntity(PostDto postDto);

    @Mapping(target = "username", source = "user.username")
    PostDto mapToDto(PostEntity postEntity);

    @Mapping(target = "id", ignore = true)
    void toEntity(PostDto postDto, @MappingTarget PostEntity postEntity);
}
