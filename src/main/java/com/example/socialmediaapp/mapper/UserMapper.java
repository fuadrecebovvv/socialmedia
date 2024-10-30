package com.example.socialmediaapp.mapper;

import com.example.socialmediaapp.dao.entities.UserEntity;
import com.example.socialmediaapp.model.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity mapToEntity(UserDto userDto);

    UserDto mapToDto(UserEntity userEntity);

    @Mapping(target = "id", ignore = true)
    void mapToEntity(UserDto userDto, @MappingTarget UserEntity userEntity);
}
