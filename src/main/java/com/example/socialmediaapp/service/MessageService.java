package com.example.socialmediaapp.service;

import com.example.socialmediaapp.dao.repositories.MessageRepository;
import com.example.socialmediaapp.dao.repositories.UserRepository;
import com.example.socialmediaapp.mapper.MessageMapper;
import com.example.socialmediaapp.model.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService {
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;
    private final UserRepository userRepository;

    public void sendMessage(MessageDto messageDto){
        log.info("MessageService.sendMessage.start");
        var receiver = userRepository.getUserEntityByUsername(messageDto.getReceiverUsername());
        var sender = userRepository.getUserEntityByUsername(messageDto.getSenderUsername());
        var messageEntity = messageMapper.toEntity(messageDto, receiver, sender);
        messageRepository.save(messageEntity);
        log.info("MessageService.sendMessage.end");
    }

    public List<MessageDto> allMessages(Long senderId, Long receiverId){
        log.info("MessageService.allMessages.start senderId {} receiverId {}", senderId, receiverId);
        var messages = messageRepository.findMessageEntitiesByUserId(senderId, receiverId)
                .stream()
                .map(messageMapper::toDto).toList();
        log.info("MessageService.allMessages.start senderId {} receiverId {}", senderId, receiverId);
        return messages;
    }
}
