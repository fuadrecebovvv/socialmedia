package com.example.socialmediaapp.controller;

import com.example.socialmediaapp.model.MessageDto;
import com.example.socialmediaapp.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {
    public final MessageService messageService;

    @PostMapping
    public void sendMessage(@RequestBody MessageDto messageDto){
        messageService.sendMessage(messageDto);
    }

    @GetMapping("/sender/{senderId}/receiver/{receiverId}")
    public List<MessageDto> allMessages(@PathVariable Long senderId,
                                        @PathVariable Long receiverId){
        return messageService.allMessages(senderId, receiverId);
    }
}
