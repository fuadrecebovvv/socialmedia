package com.example.socialmediaapp.mapper;

import com.example.socialmediaapp.dao.entities.MessageEntity;
import com.example.socialmediaapp.dao.entities.UserEntity;
import com.example.socialmediaapp.model.MessageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;

@Mapper(componentModel = "spring", imports = LocalDate.class)
public abstract class MessageMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sentAt", expression = "java(LocalDate.now())")
    public abstract MessageEntity toEntity(MessageDto messageDto, UserEntity receiver, UserEntity sender);

    @Mapping(target = "senderUsername", source = "sender.username")
    @Mapping(target = "receiverUsername", source = "receiver.username")
    public abstract MessageDto toDto(MessageEntity messageEntity);


}
