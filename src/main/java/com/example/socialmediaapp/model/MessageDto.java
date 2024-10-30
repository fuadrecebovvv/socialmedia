package com.example.socialmediaapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    private Long id;
    private String senderUsername;
    private String receiverUsername;
    private String message;
    private LocalDate sentAt;
}
